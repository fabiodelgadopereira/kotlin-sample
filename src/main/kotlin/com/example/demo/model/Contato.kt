package com.example.demo.model

import javax.validation.constraints.Email
import javax.validation.constraints.Size

class Contato (
        @get:Size(min=3, max=15)
        val nome: String,
        @field:Email @get:Size(min=3, max=300)
        val email: String,
        @get:Size(min=3, max=500)
        val mensagem: String
)

