package com.ideas2It.icare.server.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideas2It.icare.server.common.Constants
import com.ideas2It.icare.server.model.dto.ProfileResponse
import com.ideas2It.icare.server.model.dto.UserDTO
import com.ideas2It.icare.server.repository.ContributorRepository
import com.ideas2It.icare.server.repository.OrganizationRepository
import com.ideas2It.icare.server.service.ProfileService
import com.nimbusds.jose.crypto.RSADecrypter
import com.nimbusds.jwt.EncryptedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.spec.PKCS8EncodedKeySpec

@Service
class ProfileServiceImpl(
        private val contributorRepository: ContributorRepository,
        private val organizationRepository: OrganizationRepository

): ProfileService {

    @Value("\${app.private-key}")
    private lateinit var privateKey: String

    var rsaPrivateKey: RSAPrivateKey? = null


    override fun getProfileDetails(token: String): ProfileResponse {
        val jwt  = EncryptedJWT.parse(token)
        if (null == rsaPrivateKey) {
            decrypt()
        }
        val decrypter = RSADecrypter(rsaPrivateKey)
        jwt.decrypt(decrypter)
        val user = jwt.jwtClaimsSet.getClaim("userdata")
        val mapper = ObjectMapper()
        val userdata = mapper.convertValue(user, UserDTO::class.java)

        val response = ProfileResponse(userdata.username, null, null)
        if (userdata.role == Constants.ROLE_CONTRIBUTOR) {
            response.contributor = contributorRepository.getContributorByUserId(userdata.id)
        } else if (userdata.role == Constants.ROLE_ORG_USER) {
            response.organization =  organizationRepository.getOrgByUserId(userdata.id)
        }

        return response
    }

    fun decrypt() {
        try {
            val resource = ClassPathResource(privateKey)
            val bdata = FileCopyUtils.copyToByteArray(resource.inputStream)
            val privateKeySpec = PKCS8EncodedKeySpec(bdata)
            val kf = KeyFactory.getInstance(Constants.RSA)
            rsaPrivateKey = kf.generatePrivate(privateKeySpec) as RSAPrivateKey
        } catch (exception: Exception) {
        }
    }




}
