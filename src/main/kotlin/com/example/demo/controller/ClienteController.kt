package com.example.demo.controller

import com.example.demo.db.ClienteRepository
import com.example.demo.model.Cliente
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/Cliente")
class ClienteController(val repository: ClienteRepository) {

    @GetMapping
    fun findAll(): ResponseEntity<List<Cliente>> {
        try {
            val allNotes = repository.findAll().toList()
            return ResponseEntity.ok(allNotes)
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PostMapping
    fun addCliente(@Valid @RequestBody cliente: Cliente): ResponseEntity<Cliente> {
        try {
            val savedNote = repository.save(cliente)
            return ResponseEntity.ok(savedNote)
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)

        }
    }

    @PutMapping("/{id}")
    fun updateCliente(@PathVariable id: Long, @RequestBody cliente: Cliente): ResponseEntity<Cliente> {
        try {
            if (repository.existsById(id)) {
                var clientUpdate: Cliente = repository.findById(id).get().copy(
                        nome = cliente.nome,
                        cidade = cliente.cidade,
                        email = cliente.email,
                        sexo = cliente.sexo
                )
                repository.save(clientUpdate)
                return ResponseEntity.ok(clientUpdate)
            }
            return ResponseEntity.notFound().build()
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }


    @DeleteMapping("/{id}")
    fun removeCliente(@PathVariable id: Long): ResponseEntity<Unit> {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id)
                return ResponseEntity.ok().build()
            }
            return ResponseEntity.notFound().build()
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Cliente> {
        try {
            if (repository.existsById(id)) {
                var clienteBusca: Cliente = repository.findById(id).get()
                return ResponseEntity.ok(clienteBusca)
            }
            return ResponseEntity.notFound().build()
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }
}