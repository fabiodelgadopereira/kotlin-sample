package com.example.KotlinApp.controller

import com.example.KotlinApp.repository.ClienteRepository
import com.example.KotlinApp.model.Cliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity


@RestController
class ClienteController(@Autowired val clienteRepository: ClienteRepository)
{



    @GetMapping("/Clientes")
    fun findAllClientes(): ResponseEntity<List<Cliente>> {
        val allClientes = clienteRepository.findAll()
        return ResponseEntity.ok(allClientes)
    }

    @GetMapping("/Cliente/{id}")
    fun getCliente(@PathVariable id: Int): ResponseEntity<Cliente> {
        val allClientes = clienteRepository.get(id)
        return ResponseEntity.ok(allClientes)
    }

    @PostMapping(value = ["/Cliente"])
    fun postCliente(): String
    {
        return "teste"
    }

    @DeleteMapping(value = ["/Cliente"])
    fun deleteCliente(): String
    {
        return "teste"
    }

    @PutMapping(value = ["/Cliente"])
    fun putCliente(): String
    {
        return "teste"
    }
}