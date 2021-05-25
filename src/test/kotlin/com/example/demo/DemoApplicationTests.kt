package com.example.demo

import com.example.demo.model.Cliente
import com.example.demo.model.Contato
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertFalse
import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory


internal  class DemoApplicationTests {

	private lateinit var validator: Validator

	@Before
	fun setUp() {
		val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
		validator = factory.validator
	}
	@Test
	fun nameMinimumSizeCliente() {
		setUp()
		val cliente: Cliente = Cliente("0", "cidade","teste@test.com","Masculino")
		val violations: Set<ConstraintViolation<Cliente>> = validator.validate(cliente)
		assertFalse(violations.isEmpty())
	}

	@Test
	fun emailFormatCliente() {
		setUp()
		val cliente: Cliente = Cliente("xsss", "cidade","1213","Masculino")
		val violations: Set<ConstraintViolation<Cliente>> = validator.validate(cliente)
		assertFalse(violations.isEmpty())
	}
	@Test
	fun nameMinimumSizeContato() {
		setUp()
		val contato: Contato = Contato("x", "teste@test.com","mensagem teste!")
		val violations: Set<ConstraintViolation<Contato>> = validator.validate(contato)
		assertFalse(violations.isEmpty())
	}

	@Test
	fun emailFormatContato() {
		setUp()
		val contato: Contato = Contato("xsss", "1213","mensagem teste!")
		val violations: Set<ConstraintViolation<Contato>> = validator.validate(contato)
		assertFalse(violations.isEmpty())
	}
}
