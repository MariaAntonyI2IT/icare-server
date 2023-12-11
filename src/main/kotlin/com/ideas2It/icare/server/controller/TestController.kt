package com.ideas2It.icare.server.controller

import com.ideas2It.icare.server.annotations.TokenValidator
import com.ideas2It.icare.server.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(private val userService: UserService) {

    @PostMapping("/check")
    fun start() : ResponseEntity<Any>? {
        println("fgfdgdfdgdgddfdf")
        return ResponseEntity<Any>(mapOf("message" to "SUCCESS"), HttpStatus.OK)
    }
}