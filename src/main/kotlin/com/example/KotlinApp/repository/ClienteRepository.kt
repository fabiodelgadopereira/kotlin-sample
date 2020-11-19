package com.example.KotlinApp.repository

import com.example.KotlinApp.dtos.ClienteForRegister
import com.example.KotlinApp.model.Cliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.stereotype.Repository
import java.sql.ResultSet


@Repository
class ClienteRepository(@Autowired val jdbcTemplate: JdbcTemplate) {

    private val SQL_FIND_ALL = "EXEC [dbo].[sp_Clientes_GetAllValues]"
    private val SQL_FIND_BY_ID  = "EXEC [dbo].[sp_Clientes_GetValueById] ?";

    private val SQL_INSERT  = "EXEC [dbo].[sp_Clientes_InsertValue] ? ,? ,? ,? ";

    var ROW_MAPPER: RowMapper<Cliente> = RowMapper<Cliente> { resultSet: ResultSet, rowIndex: Int ->
        Cliente(resultSet.getInt("Id"), resultSet.getString("Nome"), resultSet.getString("Cidade"), resultSet.getString("Email"), resultSet.getString("Sexo"))
    }

    fun findAll(): List<Cliente> {
        return jdbcTemplate.query(SQL_FIND_ALL, ROW_MAPPER)
    }
    fun get(id: Int): Cliente {
        //todo: utilizar parameters
        return jdbcTemplate.query(SQL_FIND_BY_ID + id, ROW_MAPPER)[0]
    }

    fun insert(clie: ClienteForRegister) {

        jdbcTemplate.update(SQL_INSERT, clie.nome,clie.cidade,clie.email,clie.sexo)

    }
}