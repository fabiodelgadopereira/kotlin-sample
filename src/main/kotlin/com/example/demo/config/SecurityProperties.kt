package com.example.demo.config

import org.hibernate.validator.constraints.Length
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt-security")
class SecurityProperties {

    @Length(min = 42, message = "Minimum length for the secret is 42.")
    @Value("\${jwt.secret}")
    lateinit var secret: String

    @Value("\${jwt.expirationTime}")
    val  expirationTime: Int = 31  // in days

    @Value("\${jwt.tokenPrefix}")
    lateinit var tokenPrefix  : String

    @Value("\${jwt.headerString}")
    lateinit var headerString : String

    @Value("\${jwt.strength}")
    val strength : Int = 10
}