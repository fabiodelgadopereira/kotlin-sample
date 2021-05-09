package com.example.demo.db

import org.springframework.data.repository.CrudRepository
import com.example.demo.model.User
import java.util.*

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
}