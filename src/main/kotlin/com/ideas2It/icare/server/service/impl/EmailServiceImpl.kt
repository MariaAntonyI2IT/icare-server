package com.ideas2It.icare.server.service.impl

import com.ideas2It.icare.server.model.dto.LoginRequestDTO
import com.ideas2It.icare.server.model.dto.OtpDTO
import com.ideas2It.icare.server.model.entity.Otp
import com.ideas2It.icare.server.model.entity.OutBoundEmail
import com.ideas2It.icare.server.repository.EmailRepository
import com.ideas2It.icare.server.repository.OtpRepository
import com.ideas2It.icare.server.service.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EmailServiceImpl : EmailService {

    @Autowired
    private lateinit var emailRepository: EmailRepository

    @Autowired
    private lateinit var otpRepository: OtpRepository

    override fun saveEmail(loginRequestDTO: LoginRequestDTO?): String {
        if (loginRequestDTO?.username != null) {
            val username: String = loginRequestDTO.username
            val otp = saveOtp(username)
            val outBoundEmail: OutBoundEmail = OutBoundEmail(
                toAddress = username,
                subject = "ICare-OTP Validation",
                body = "OTP for your registration is $otp"
            )
            emailRepository.save(outBoundEmail)
            return "Email sent successfully"
            }
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid login request")
    }


    override fun saveOtp(email: String?): String {
        val randomPin = ((Math.random() * 9000).toInt() + 1000).toString()
        val existingUser: Otp? = email?.let { otpRepository.findByUsername(it) }
        var otp: Otp = Otp(otp =  randomPin, username = email)
        if (existingUser != null) {
            if (existingUser.isVerified) {
                throw ResponseStatusException(HttpStatus.CONFLICT, "User already exists")
            } else {
                otp = Otp(id = existingUser.id, otp =  randomPin, username = email)
            }
        }
        otpRepository.save(otp)
        return randomPin
    }

    override fun validateOtp(otpDTO: OtpDTO?): String {
        if (otpDTO?.username != null) {
            val username: String = otpDTO.username
            val otp: Otp? = otpRepository.findByUsernameAndIsVerifiedFalse(username)
            println("otp $otp")
            if (otp != null && otp.otp.equals(otpDTO.otp)) {
                otp.isVerified = true
                otpRepository.save(otp)
                return "Valid OTP"
            }
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP does not match")
        }
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Request")
    }

    override fun getOutBoundEmails(): List<OutBoundEmail> {
       return emailRepository.findByIsProcessedFalseAndRetryAttemptsLessThan(2);
    }

    override fun updateOutBoundEmails(outBoundEmails: List<OutBoundEmail>): List<OutBoundEmail> {
        return emailRepository.saveAll(outBoundEmails);
    }

}