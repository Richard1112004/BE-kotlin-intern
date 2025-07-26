package com.example.demo.component

import com.example.demo.model.UserModel
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component


@Component
class JWTPasswordAuth {
    fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(plainPassword, hashedPassword)
    }
    fun verifyOrThrow(rawPassword: String, user: UserModel) {
        if (!verifyPassword(rawPassword, user.password!!)) {
            throw RuntimeException("Invalid password")
        }
    }
}
