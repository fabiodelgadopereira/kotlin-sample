package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name = "app_role", catalog="CadastroDB", schema="dbo")
class Role(
        @Id
        @GeneratedValue()
        val id: Int,

        @Column(name = "role_name")
        var roleName: String? = null,

        @Column(name = "description")
        var description: String? = null
)