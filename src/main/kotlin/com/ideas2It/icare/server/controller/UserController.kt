package com.ideas2It.icare.server.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ideas2It.icare.server.service.UserService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/")
class UserController(private val userService: UserService) {

    @PostMapping("login/oAuth")
    fun registerWithOauth(@RequestParam("token") token: String, httpResponse : HttpServletResponse) {
        val response = userService.getGoogleProfile(token)
        if (response.isNotBlank()) {
            httpResponse.setHeader("Authorization", response)
            val objectWriter = ObjectMapper().writer().withDefaultPrettyPrinter()
        } else {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized Access")
        }
        userService.getGoogleProfile(token)
    }
}