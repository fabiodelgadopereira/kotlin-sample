package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name="Clientes", catalog="CadastroDB", schema="dbo")
class Cliente(
        var nome: String = "",
        var cidade: String = "",
        var email: String = "",
        var sexo: String = "",
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)