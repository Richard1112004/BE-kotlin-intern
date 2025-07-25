package com.example.demo.repository

import com.example.demo.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo : JpaRepository<UserModel, Long> {
    fun findByEmail(username: String): UserModel?
}