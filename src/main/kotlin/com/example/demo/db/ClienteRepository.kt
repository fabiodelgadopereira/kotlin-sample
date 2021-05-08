package com.example.demo.db

import com.example.demo.model.Cliente
import org.springframework.data.repository.CrudRepository


interface ClienteRepository : CrudRepository<Cliente, Long> {
    fun findById(name: String): List<Cliente>
}