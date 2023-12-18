package com.ideas2It.icare.server.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler

class LoginFailure() : SimpleUrlAuthenticationFailureHandler() {

    override fun onAuthenticationFailure(
            request: HttpServletRequest,
            response: HttpServletResponse,
            exception: AuthenticationException
    ) {
        val responseBody = mapOf("message" to exception.message)
        response.contentType = "application/json;charset=UTF-8"
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.writer.write(ObjectMapper().writeValueAsString(responseBody))
    }


}