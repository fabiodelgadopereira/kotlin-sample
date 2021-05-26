package com.example.demo.controller


import com.example.demo.model.Cliente
import com.example.demo.service.EmailSenderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/Contato")
class ContatoController(private val emailSenderService: EmailSenderService) {

    @PostMapping()
    fun sendSimpleEmail(@RequestBody request: EmailRequest): ResponseEntity<String> {
        try {
            emailSenderService.sendEmail(
                    subject = request.subject!!,
                    targetEmail = request.targetEmail!!,
                    text = request.text!!
            )
            return ResponseEntity.ok("E-mail enviado com sucesso!! ")
            // some code
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar enviar e-mail :(")

        }
    }
}

class EmailRequest(
        val subject: String?,
        val targetEmail: String?,
        val text: String?,
        val name: String?
)