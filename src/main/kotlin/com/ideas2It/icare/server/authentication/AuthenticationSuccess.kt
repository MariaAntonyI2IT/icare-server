package com.ideas2It.icare.server.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideas2It.icare.server.common.Constants
import com.ideas2It.icare.server.model.dto.UserDTO
import com.ideas2It.icare.server.model.entity.User
import com.ideas2It.icare.server.model.entity.UserToken
import com.ideas2It.icare.server.repository.UserTokenRepository
import com.nimbusds.jose.EncryptionMethod
import com.nimbusds.jose.JWEAlgorithm
import com.nimbusds.jose.JWEHeader
import com.nimbusds.jose.crypto.RSAEncrypter
import com.nimbusds.jwt.EncryptedJWT
import com.nimbusds.jwt.JWTClaimsSet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.util.FileCopyUtils
import java.io.IOException
import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.*

class AuthenticationSuccess() : SimpleUrlAuthenticationSuccessHandler() {

    @Value("\${app.public-key}")
    private lateinit var publicKey: String

    @Autowired
    private lateinit var userTokenRepository: UserTokenRepository

    override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
        if (response != null) {
            if (!response.isCommitted) {
                try {
                    val userDTO = getLogInUser()
                    if (userDTO != null) {
                        val userData = mapOf("username" to (userDTO.username), "name" to (userDTO.firstName), "role" to (userDTO.role.name))
                        val token = tokenCreation(userData)
                        response.setHeader("Authorization", token)
                        val objectWriter = ObjectMapper().writer().withDefaultPrettyPrinter()
                        response.writer.write(objectWriter.writeValueAsString(userDTO))
                        userTokenRepository.save(UserToken(token, addMinutesToCurrentDate(120)))
                    } else {
                        response.writer.write("{ \"error\": \"Invalid User\"}")
                    }

                } catch (_: IOException) {

                }
            }
        }
    }

    fun getLogInUser(): UserDTO? {
        val authentication = SecurityContextHolder.getContext()?.authentication
        if (authentication == null || authentication.principal == null) {
            return null
        }
        if (authentication.principal == "anonymousUser") {
            return null
        }
        val principal = authentication.principal as User
        val userDTO : UserDTO = UserDTO()
        userDTO.firstName = principal.firstName
        userDTO.lastName = principal.lastName
        userDTO.username = principal.username
        userDTO.role = principal.role
        return userDTO
    }

    private fun addMinutesToCurrentDate(minutes: Int): Date {
        val date = Date()
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.MINUTE, minutes)
        return cal.time
    }

    private fun getRsaPublicKey(): RSAPublicKey {
        val publicKeyResource = ClassPathResource(publicKey)
        val bdata = FileCopyUtils.copyToByteArray(publicKeyResource.inputStream)
        val spec = X509EncodedKeySpec(bdata)
        val kf = KeyFactory.getInstance(Constants.RSA)
        val publicRsaKey: RSAPublicKey = kf.generatePublic(spec) as RSAPublicKey
        return publicRsaKey
    }

    private fun tokenCreation(userData: Map<String, String?>): String {
        val jwt = JWTClaimsSet.Builder()
        jwt.issuer("")
        jwt.claim("userdata", userData)
        jwt.expirationTime(addMinutesToCurrentDate(120))
        jwt.jwtID(UUID.randomUUID().toString())
        val header = JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A128GCM)
        val encrypter = RSAEncrypter(getRsaPublicKey())
        val encryptedJWT = EncryptedJWT(header, jwt.build())
        encryptedJWT.encrypt(encrypter)
        return encryptedJWT.serialize().toString()
    }

}