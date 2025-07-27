package com.example.demo.repository.user

import com.example.demo.model.user.UserModel
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo : JpaRepository<UserModel, Long> {
    fun findByEmail(username: String): UserModel?
}