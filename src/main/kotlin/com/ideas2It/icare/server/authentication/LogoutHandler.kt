package com.ideas2It.icare.server.authentication

import com.ideas2It.icare.server.repository.UserTokenRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.web.server.ResponseStatusException

class LogoutHandler : LogoutSuccessHandler {

    @Autowired
    private lateinit var userTokenRepository: UserTokenRepository

    override fun onLogoutSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {

        val token : String? = request?.getHeader(HttpHeaders.AUTHORIZATION)
        val userToken = userTokenRepository.findByToken(token)
        if (null == userToken) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized Access")
        } else {
            userToken.token = null
            userTokenRepository.save(userToken)
        }
    }
}