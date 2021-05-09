package com.example.demo.config

import org.hibernate.validator.constraints.Length
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt-security")
class SecurityProperties {
    @Length(min = 42, message = "Minimum length for the secret is 42.")
    var secret = "g6IJFdFhSBy8UwPhYaZ9l10XMl0XBwCisKrynxU7zI5J5KrTlHTM9JkMwbjxMmU5yDtb1E"
    var expirationTime: Int = 31 // in days
    var tokenPrefix = "Bearer "
    var headerString = "Authorization"
    var strength = 10
}