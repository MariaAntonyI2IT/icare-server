package com.ideas2It.icare.server.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideas2It.icare.server.common.CommonUtil
import com.ideas2It.icare.server.common.Constants
import com.ideas2It.icare.server.model.dto.UserDTO
import com.ideas2It.icare.server.model.entity.Contributor
import com.ideas2It.icare.server.model.entity.Role
import com.ideas2It.icare.server.model.entity.User
import com.ideas2It.icare.server.model.entity.UserToken
import com.ideas2It.icare.server.repository.ContributorRepository
import com.ideas2It.icare.server.repository.RoleRepository
import com.ideas2It.icare.server.repository.UserRepository
import com.ideas2It.icare.server.repository.UserTokenRepository
import com.ideas2It.icare.server.service.UserService
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException
import java.net.URI
import java.net.URLEncoder
import java.util.*
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private


@Service
class UserServiceImpl(
        private val userRepository: UserRepository,
        private val roleRepository: RoleRepository,
        private val contributorRepository: ContributorRepository,
        private val commonUtil: CommonUtil,
        private val userTokenRepository: UserTokenRepository
) : UserService{

    override fun getGoogleProfile(accessToken: String): String {
        var token : String = ""
        val apiUrl = URI("https://www.googleapis.com/oauth2/v3/userinfo/?access_token=" +  URLEncoder.encode(accessToken))
        val requestEntity = RequestEntity<Any>(HttpMethod.GET, apiUrl)
        val restTemplate = RestTemplate()
        try {
            val responseEntity: ResponseEntity<String> = restTemplate.exchange(requestEntity, String::class.java)
            println(responseEntity.body)
            if (responseEntity.statusCode.is2xxSuccessful) {
                val userProfile = responseEntity.body
                if (userProfile != null) {
                    val objectMapper = ObjectMapper()
                    val userData = objectMapper.readValue(userProfile, Map::class.java)
                    println("user data" + userData)
                    token = createOAuthUserAndGenerateToken(userData)
                    userTokenRepository.save(UserToken(token, commonUtil.addMinutesToCurrentDate(120)))
                }
            }
        } catch (e: Exception) {
            println("Exception during Google Profile API call: ${e}")
        }
        return token
    }

    fun createOAuthUserAndGenerateToken(authData: Map<*, *>): String {
        val contributorRole = roleRepository.findByName(Constants.ROLE_CONTRIBUTOR)
        var token: String = ""
        if (null == authData["email"]) {
            return token
        }
        var user:User? = userRepository.findByUsername(authData["email"].toString())

        if (null == user) {
            var newuser = User(authData["email"].toString(), null, contributorRole)
            newuser = userRepository.save(newuser)
            var contributor = Contributor()
            contributor.firstName = authData["given_name"].toString()
            contributor.lastName = authData["family_name"].toString()
            contributor.avatar = authData["picture"].toString()
            contributor.email = authData["email"].toString()
            contributor.user = newuser
            user = newuser
            contributor = contributorRepository.save(contributor)

        }

        token = commonUtil.tokenCreation(mapOf("username" to (user.username), "id" to (user.id),"role" to (user.role.name)))
        return token
    }
}