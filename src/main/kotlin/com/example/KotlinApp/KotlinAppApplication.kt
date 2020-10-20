package com.example.KotlinApp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinAppApplication

fun main(args: Array<String>) {
	runApplication<KotlinAppApplication>(*args)
}
