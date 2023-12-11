package com.ideas2It.icare.server.service.impl

import com.ideas2It.icare.server.common.Constants
import com.ideas2It.icare.server.model.dto.LoginRequestDTO
import com.ideas2It.icare.server.model.entity.User
import com.ideas2It.icare.server.repository.UserRepository
import com.ideas2It.icare.server.service.UserService
import com.nimbusds.jose.EncryptionMethod
import com.nimbusds.jose.JWEAlgorithm
import com.nimbusds.jose.JWEHeader
import com.nimbusds.jose.crypto.RSAEncrypter
import com.nimbusds.jwt.EncryptedJWT
import com.nimbusds.jwt.JWTClaimsSet
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils
import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.*

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService{
//    @PostMapping()
//    fun authenticateUser(@RequestBody loginRequest : LoginRequestDTO, httpResponse : HttpServletResponse) {
//        val response = userService.authenticateUser(loginRequest)
//        if (response.isNotEmpty()) {
//            httpResponse.setHeader("Authorization", response["token"].toString())
//            val objectWriter = ObjectMapper().writer().withDefaultPrettyPrinter()
//            httpResponse.writer.write(objectWriter.writeValueAsString(response["userData"]))
//        } else {
//            httpResponse.writer.write("{ \"error\": \"Invalid User\"}")
//        }
//    }


}