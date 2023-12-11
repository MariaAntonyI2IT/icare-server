package com.ideas2It.icare.server.annotations

import com.ideas2It.icare.server.repository.UserTokenRepository
import io.jsonwebtoken.ExpiredJwtException
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Aspect
@Component
class ValidateToken {

    @Autowired
    private lateinit var userTokenRepository: UserTokenRepository

    @Before("@annotation(com.ideas2It.icare.server.annotations.TokenValidator)")
    fun validateToken(joinPoint: JoinPoint) {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val token = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (null != token) {
            val userToken = userTokenRepository.findByToken(token)
            if (null == userToken) {
                throw ExpiredJwtException(null,null, "Token expired")
            } else if (userToken.tokenExpireTime?.after(Date()) == true){
                userToken.tokenExpireTime = addMinutesToCurrentDate(60)
                userTokenRepository.save(userToken)
            } else {
                throw ExpiredJwtException(null,null, "Token expired")
            }
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized Access")
        }


    }

    private fun addMinutesToCurrentDate(minutes: Int): Date {
        val date = Date()
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.MINUTE, minutes)
        return cal.time
    }
}