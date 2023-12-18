package com.ideas2It.icare.server.common

import com.nimbusds.jose.EncryptionMethod
import com.nimbusds.jose.JWEAlgorithm
import com.nimbusds.jose.JWEHeader
import com.nimbusds.jose.crypto.RSAEncrypter
import com.nimbusds.jwt.EncryptedJWT
import com.nimbusds.jwt.JWTClaimsSet
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.util.FileCopyUtils
import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.*

@Component
class CommonUtil {

    @Value("\${app.public-key}")
    private lateinit var publicKey: String

    fun tokenCreation(userData: Map<String, Any?>): String {
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

    fun addMinutesToCurrentDate(minutes: Int): Date {
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
}