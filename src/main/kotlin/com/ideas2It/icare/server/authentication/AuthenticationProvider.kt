package com.ideas2It.icare.server.authentication

import com.ideas2It.icare.server.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

class AuthenticationProvider() : AuthenticationProvider {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun authenticate(authentication: Authentication?): Authentication {
        println("auth provider")
        val username: String? = (authentication?.principal as? String)?.lowercase()
        val password :String? = authentication?.credentials as? String
        val user = userRepository.findByUsernameAndIsDeletedFalse(username?.lowercase())
        if (user?.password != null && user.password.equals(password)) {
            val authorities = user.authorities
            return UsernamePasswordAuthenticationToken(user, password, authorities)
        }
        throw BadCredentialsException("Invalid Credentials")
    }

    override fun supports(authentication: Class<*>?): Boolean {
        val isAssignable = authentication is UsernamePasswordAuthenticationToken
        return true
    }


}