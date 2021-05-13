package com.example.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.SimpleMailMessage

@Configuration
class TemplateConfig {
    @Bean
    fun exampleNewsletterTemplate(): SimpleMailMessage {
        val template = SimpleMailMessage()

        template.setSubject("Newsletter")
        template.setText("""
            Hello %s, 
            
            This is example newsletter message
        """.trimIndent()
        )

        return template
    }
}