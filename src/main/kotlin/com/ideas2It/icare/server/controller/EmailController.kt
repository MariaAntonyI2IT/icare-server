package com.ideas2It.icare.server.controller

import com.ideas2It.icare.server.model.dto.LoginRequestDTO
import com.ideas2It.icare.server.model.dto.OtpDTO
import com.ideas2It.icare.server.service.EmailService
import com.ideas2It.icare.server.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/email")
class EmailController(private val emailService: EmailService) {

    @PostMapping("/send-otp")
    fun sendOtp(@RequestBody loginRequestDTO: LoginRequestDTO?) : ResponseEntity<Any> {
        return emailService.saveEmail(loginRequestDTO)
//        return ResponseEntity.ok().body(mapOf("message" to "OTP sent successfully"))
    }

    @PostMapping("/validate-otp")
    fun validateOtp(@RequestBody otpDto: OtpDTO?) : ResponseEntity<Any> {
        return emailService.validateOtp(otpDto)
//        return ResponseEntity.ok().body(mapOf("message" to "OTP is validated"))
    }
}