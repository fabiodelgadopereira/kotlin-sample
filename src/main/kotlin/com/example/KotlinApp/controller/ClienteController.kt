package com.example.KotlinApp.controller

import org.springframework.web.bind.annotation.*


@RestController
class ClienteController
{


    @GetMapping("/Clientes")
    fun findAllClientes(): String
    {
        return "teste"
    }

    @GetMapping("/Cliente/{id}")
    @ResponseBody
    fun getCliente(): String
    {
        return "teste"
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