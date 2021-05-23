package com.example.demo.controller

import com.example.demo.db.ClienteRepository
import com.example.demo.model.Cliente
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
    fun findAll() = repository.findAll()

    @PostMapping
    fun addCliente(@Valid @RequestBody Cliente: Cliente)
            = repository.save(Cliente)

    @PutMapping("/{id}")
    fun updateCliente(@PathVariable id: Long, @RequestBody Cliente: Cliente) {
        assert(Cliente.id == id)
        repository.save(Cliente)
    }

    @DeleteMapping("/{id}")
    fun removeCliente(@PathVariable id: Long)
            = repository.deleteById(id)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long)
            = repository.findById(id)
}