package com.example.KotlinApp.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping( "/Clientes")
class ClienteController
{
    @GetMapping
    fun home(): String
    {
        return "teste"
    }
}