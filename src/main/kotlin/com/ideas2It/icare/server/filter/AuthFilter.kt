package com.ideas2It.icare.server.filter

import com.ideas2It.icare.server.repository.UserRepository
import com.ideas2It.icare.server.repository.UserTokenRepository
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Component
class AuthFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var userTokenRepository: UserTokenRepository

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val requestUri = request.requestURI
        println(request.requestURI)
        val token = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (null == token || token.isBlank()) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized Access")
        }
        val userToken = userTokenRepository.findByToken(token)
        if (null == userToken) {
            throw ExpiredJwtException(null,null, "Token expired")
        } else if (userToken.tokenExpireTime?.after(Date()) == true){
            userToken.tokenExpireTime = addMinutesToCurrentDate(60)
            userTokenRepository.save(userToken)
        } else {
            throw ExpiredJwtException(null,null, "Token expired")
        }
        filterChain.doFilter(request,response)
    }

    private fun addMinutesToCurrentDate(minutes: Int): Date {
        val date = Date()
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.MINUTE, minutes)
        return cal.time
    }

}