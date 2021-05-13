package com.example.demo.controller

import com.example.demo.db.ClienteRepository
import com.example.demo.model.Contato
import com.example.demo.service.EmailSenderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/Contato")
class ContatoController(

    private val emailSenderService: EmailSenderService
    ) {

        @PostMapping("/api/email")
        fun sendSimpleEmail(
                @RequestBody request: EmailRequest
        ): String {

            try {
                emailSenderService.sendEmail(
                        subject = request.subject!!,
                        targetEmail = request.targetEmail!!,
                        text = request.text!!
                )

                return "E-mail enviado com sucesso!! "
                // some code
            } catch (e : Exception) {
                return "Erro ao tentar enviar e-mail :("
            }
        }
}

class EmailRequest(
        val subject: String?,
        val targetEmail: String?,
        val text: String?,
        val name: String?
)