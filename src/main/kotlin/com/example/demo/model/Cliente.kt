package com.example.demo.model

import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name="Clientes", catalog="CadastroDB", schema="dbo")
data class Cliente(
        @get:Size(min=3, max=15)
        var nome: String,
        @get:Size(min=3, max=50)
        var cidade: String,
        @get:Size(min=3, max=300)
        var email: String,
        @get:Size(min=3, max=15)
        var sexo: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
)