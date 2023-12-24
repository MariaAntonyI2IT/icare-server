package com.ideas2It.icare.server.service.impl

import com.ideas2It.icare.server.model.dto.LoginRequestDTO
import com.ideas2It.icare.server.model.dto.OtpDTO
import com.ideas2It.icare.server.model.entity.Otp
import com.ideas2It.icare.server.model.entity.OutBoundEmail
import com.ideas2It.icare.server.repository.EmailRepository
import com.ideas2It.icare.server.repository.OtpRepository
import com.ideas2It.icare.server.repository.UserRepository
import com.ideas2It.icare.server.service.EmailService
import com.sendgrid.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EmailServiceImpl : EmailService {

    @Autowired
    private lateinit var emailRepository: EmailRepository

    @Autowired
    private lateinit var otpRepository: OtpRepository

    @Autowired
    private lateinit var userRepository: UserRepository;

    override fun saveEmail(loginRequestDTO: LoginRequestDTO?): ResponseEntity<Any> {
        if (loginRequestDTO?.username != null) {
            val user = loginRequestDTO.username.let { userRepository.findByUsername(it) }
            if (user != null) {
                return ResponseEntity<Any>(mapOf("message" to "User Already exists"), HttpStatus.NOT_ACCEPTABLE)
            }
            val username: String = loginRequestDTO.username
            val otp = saveOtp(username)
            val outBoundEmail: OutBoundEmail = OutBoundEmail(
                toAddress = username,
                subject = "ICare-OTP Validation",
                body = "OTP for your registration is $otp"
            )
            val email =  emailRepository.save(outBoundEmail)
            sendEmail(email)
            return ResponseEntity<Any>(mapOf("message" to "OTP sent successfully"), HttpStatus.OK)
            }
        return ResponseEntity<Any>(mapOf("message" to "Invalid login request"), HttpStatus.BAD_REQUEST)


    }


    override fun saveOtp(email: String?): String {
        val randomPin = ((Math.random() * 9000).toInt() + 1000).toString()
        val existingUser: Otp? = email?.let { otpRepository.findByUsername(it) }
        var otp: Otp = Otp(otp =  randomPin, username = email)
        if (existingUser != null) {
//            if (existingUser.isVerified) {
//                throw ResponseStatusException(HttpStatus.CONFLICT, "User already exists")
//            } else {
                otp = Otp(id = existingUser.id, otp =  randomPin, username = email, isVerified = false)
//            }
        }
        otpRepository.save(otp)
        return randomPin
    }

    override fun validateOtp(otpDTO: OtpDTO?): ResponseEntity<Any> {
        if (otpDTO?.username != null) {
            val username: String = otpDTO.username
            val otp: Otp? = otpRepository.findByUsernameAndIsVerifiedFalse(username)
            println("otp $otp")
            if (otp != null && otp.otp.equals(otpDTO.otp)) {
                otp.isVerified = true
                otpRepository.save(otp)
                return ResponseEntity<Any>(mapOf("message" to "OTP is validated"), HttpStatus.OK)
            }
            return ResponseEntity<Any>(mapOf("message" to "OTP does not match"), HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity<Any>(mapOf("message" to "OTP does not match"), HttpStatus.BAD_REQUEST)
    }

    override fun getOutBoundEmails(): List<OutBoundEmail> {
       return emailRepository.findByIsProcessedFalseAndRetryAttemptsLessThan(2);
    }

    override fun updateOutBoundEmails(outBoundEmails: List<OutBoundEmail>): List<OutBoundEmail> {
        return emailRepository.saveAll(outBoundEmails);
    }

    fun sendEmail(outBoundEmail:OutBoundEmail) {
        val mail = Mail()
        mail.setSubject(outBoundEmail.subject)
        mail.addContent(Content("text/HTML", outBoundEmail.body))
        mail.setFrom(Email("divya.s@ideas2it.com"))
        val personalization = Personalization()
        personalization.addTo(Email(outBoundEmail.toAddress))
        mail.addPersonalization(personalization)
        val sendGrid = SendGrid("")
        val request = Request()
        request.method = Method.POST
        request.endpoint = "mail/send"
        request.body = mail.build()
        val response = sendGrid.api(request)
    }
}