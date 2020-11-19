package com.example.KotlinApp.controller

import com.example.KotlinApp.repository.ClienteRepository
import com.example.KotlinApp.model.Cliente
import com.example.KotlinApp.dtos.ClienteForRegister
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
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
        try {
            val allClientes = clienteRepository.get(id)
            return ResponseEntity.ok(allClientes)
        } catch (exception: Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body( null);
        }
    }

    @PostMapping(value = ["/Cliente"])
    fun postCliente( @RequestBody cli: ClienteForRegister): ResponseEntity<String>
    {
        try {
            val allClientes = clienteRepository.insert(cli)
            return ResponseEntity.status(HttpStatus.OK)
                    .body( "Cliente cadastrado com sucesso")
        } catch (exception: Exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body( "Erro ao tentar cadastrar cliente: "+exception.message)
        }
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