package com.example.demo.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import javax.mail.internet.MimeMessage

@Service
class EmailSenderService(
        private val emailSender: JavaMailSender,
        private val template: SimpleMailMessage,
        @Value("classpath:file.txt")
        private val resourceFile: Resource
) {

    fun sendEmail(
            subject: String,
            text: String,
            targetEmail: String
    ) {
        val message = SimpleMailMessage()

        message.setSubject(subject)
        message.setText(text)
        message.setTo(targetEmail)

        emailSender.send(message)
    }

    fun sendEmailUsingTemplate(
            name: String,
            targetEmail: String
    ) {
        val message = SimpleMailMessage(template)

        val text = template.text
        message.setText(text!!.format(name))
        message.setTo(targetEmail)

        emailSender.send(message)
    }

    fun sendEmailWithAttachment(
            targetEmail: String
    ) {
        val message: MimeMessage = emailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)

        helper.setTo(targetEmail)
        helper.setSubject("Email with attachment")
        helper.setText("Please see the file in attachment")
        helper.addAttachment("file.txt", resourceFile)

        emailSender.send(message)
    }
}