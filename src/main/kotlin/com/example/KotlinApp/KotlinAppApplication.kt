package com.example.KotlinApp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@SpringBootApplication
class KotlinAppApplication {
@Bean
fun api() =   Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.example.KotlinApp.controller"))
		.paths(PathSelectors.any())
		.build()
private fun apiInfo() = ApiInfoBuilder()
		.title("Reactor test API")
		.version("1.0")
		.build()
}

fun main(args: Array<String>) {
	runApplication<KotlinAppApplication>(*args)
}
