package com.example.KotlinApp.repository

import com.example.KotlinApp.model.Cliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.stereotype.Repository
import java.sql.ResultSet


@Repository
class ClienteRepository(@Autowired val jdbcTemplate: JdbcTemplate) {

    private val SQL_FIND_ALL = "EXEC [dbo].[sp_Clientes_GetAllValues]"
    private val SQL_FIND_BY_ID  = "EXEC [dbo].[sp_Clientes_GetValueById] 1";


    var ROW_MAPPER: RowMapper<Cliente> = RowMapper<Cliente> { resultSet: ResultSet, rowIndex: Int ->
        Cliente(resultSet.getInt("Id"), resultSet.getString("Nome"), resultSet.getString("Cidade"), resultSet.getString("Email"), resultSet.getString("Sexo"))
    }

    fun findAll(): List<Cliente> {
        return jdbcTemplate.query(SQL_FIND_ALL, ROW_MAPPER)
    }
    fun get(id: Int): Cliente {
        val paramSource: SqlParameterSource = MapSqlParameterSource("id", id)
        return jdbcTemplate.query(SQL_FIND_BY_ID, ROW_MAPPER)[0]
    }
}