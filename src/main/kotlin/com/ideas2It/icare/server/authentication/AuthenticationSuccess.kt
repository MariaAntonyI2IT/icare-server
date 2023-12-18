package com.ideas2It.icare.server.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideas2It.icare.server.common.CommonUtil
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

    @Autowired
    private lateinit var userTokenRepository: UserTokenRepository

    @Autowired
    private lateinit var commonUtil: CommonUtil

    override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
        if (response != null) {
            if (!response.isCommitted) {
                try {
                    val userDTO = getLogInUser()
                    if (userDTO != null) {
                        val userData = mapOf("username" to (userDTO.username), "id" to (userDTO.id),"role" to (userDTO.role))
                        val token = commonUtil.tokenCreation(userData)
                        response.setHeader("Authorization", token)
                        response.contentType = "application/json;charset=UTF-8"
                        val objectWriter = ObjectMapper().writer().withDefaultPrettyPrinter()
                        response.writer.write(objectWriter.writeValueAsString(userDTO))
                        userTokenRepository.save(UserToken(token, commonUtil.addMinutesToCurrentDate(120)))
                    } else {
                        response.writer.write("{ \"error\": \"Invalid User\"}")
                    }

                } catch (_: IOException) {

                }
            }
        }
    }

    fun getLogInUser(): UserDTO? {
        println("getting login user")
        val authentication = SecurityContextHolder.getContext()?.authentication
        if (authentication == null || authentication.principal == null) {
            return null
        }
        if (authentication.principal == "anonymousUser") {
            return null
        }
        val principal = authentication.principal as User
        println(principal)
        val userDTO : UserDTO = UserDTO()
        userDTO.id = principal.id!!
        userDTO.username = principal.username
        userDTO.role = principal.role.name.toString()
        return userDTO
    }
}