package com.ideas2It.icare.server.service

import com.ideas2It.icare.server.model.dto.LoginRequestDTO
import com.ideas2It.icare.server.model.dto.OtpDTO
import com.ideas2It.icare.server.model.entity.OutBoundEmail
import org.springframework.stereotype.Service

interface EmailService {
    fun saveOtp(loginRequestDTO: String?): String
    fun saveEmail(loginRequestDTO: LoginRequestDTO?): String
    fun validateOtp(otpDto: OtpDTO?): String
    fun getOutBoundEmails(): List<OutBoundEmail>
    fun updateOutBoundEmails(outBoundEmails: List<OutBoundEmail>): List<OutBoundEmail>
}