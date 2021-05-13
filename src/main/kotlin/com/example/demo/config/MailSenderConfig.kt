package com.example.demo.config


import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

@Profile("manual")
@ConstructorBinding
@ConfigurationProperties(prefix = "mail-sender")
class MailSenderProperties(
        val host: String,
        val port: Int,
        val username: String,
        val password: String,
        val protocol: String,
        val auth: Boolean,
        val starttlsEnable: Boolean,
        val debug: Boolean
)

@Profile("manual")
@Configuration
@EnableConfigurationProperties(MailSenderProperties::class)
class MailSenderConfig(
        private val mailSenderProperties: MailSenderProperties
) {
    @Bean
    fun javaMailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = mailSenderProperties.host
        mailSender.port = mailSenderProperties.port
        mailSender.username = mailSenderProperties.username
        mailSender.password = mailSenderProperties.password

        configureJavaMailProperties(mailSender.javaMailProperties)
        return mailSender
    }

    private fun configureJavaMailProperties(properties: Properties) {
        properties["mail.transport.protocol"] = mailSenderProperties.protocol
        properties["mail.smtp.auth"] = mailSenderProperties.auth
        properties["mail.smtp.starttls.enable"] = mailSenderProperties.starttlsEnable
        properties["mail.debug"] = mailSenderProperties.debug
    }
}