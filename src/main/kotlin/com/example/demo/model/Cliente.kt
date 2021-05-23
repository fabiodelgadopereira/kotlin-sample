package com.example.demo.model

import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.*

@Entity
@Table(name="Clientes", catalog="CadastroDB", schema="dbo")
data class Cliente(
        @get:Size(min=3, max=15)
        var nome: String,
        @get:Size(min=3, max=50)
        var cidade: String,
        @field:Email @get:Size(min=3, max=300)
        var email: String,
        @get:Size(min=3, max=15)
        var sexo: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
         var id: Long = 0,
)