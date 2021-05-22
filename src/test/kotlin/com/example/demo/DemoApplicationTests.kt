package com.example.demo

import com.example.demo.model.Cliente
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
	fun nameMinimumSize() {
		setUp()
		val cliente: Cliente = Cliente("x", "cidade","teste@test.com","Masculino")
		val violations: Set<ConstraintViolation<Cliente>> = validator.validate(cliente)
		assertFalse(violations.isEmpty())
	}
}
