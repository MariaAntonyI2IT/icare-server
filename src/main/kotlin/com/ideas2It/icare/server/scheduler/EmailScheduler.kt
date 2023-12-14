package com.ideas2It.icare.server.scheduler

import com.ideas2It.icare.server.model.entity.OutBoundEmail
import com.ideas2It.icare.server.service.EmailService
import com.sendgrid.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
open class EmailScheduler {

    @Autowired
    private lateinit var emailService: EmailService

  //  @Scheduled(fixedDelay = 10000)
    fun emailScheduler(): Unit {
        println("aaaaaaaaaaaaaaaaaaaaaaaaaaa")
        val mails: List<OutBoundEmail> = emailService.getOutBoundEmails()
        println("mails $mails")
        val emailsListToUpdate: MutableList<OutBoundEmail> = ArrayList<OutBoundEmail>()
        if (mails.isNotEmpty()) {
            for (outBoundEmail in mails) {
                println("outbound mail $outBoundEmail")
                val mail = Mail()
                mail.setSubject(outBoundEmail.subject)
                mail.addContent(Content("text/HTML", outBoundEmail.body))
                mail.setFrom(Email("divya.s@ideas2it.com"))
                val personalization = Personalization()
                personalization.addTo(Email(outBoundEmail.toAddress))
                mail.addPersonalization(personalization)
                val sendGrid = SendGrid("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")
                val request = Request()
                request.method = Method.POST
                request.endpoint = "mail/send"
                request.body = mail.build()
                val response = sendGrid.api(request)
                println("response ${response.statusCode}" )
                if (response.statusCode == 202) {
                    outBoundEmail.isProcessed = true
                } else {
                    val retryAttempts: Int = outBoundEmail.retryAttempts
                    outBoundEmail.retryAttempts = (retryAttempts + 1)
                }
                emailsListToUpdate.add(outBoundEmail)
            }
            emailService.updateOutBoundEmails(emailsListToUpdate)
        }
    }
}