package com.example.demo.component

import com.example.demo.model.user.UserModel
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component


@Component
class JWTPasswordAuth {
    fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(plainPassword, hashedPassword)
    }
    fun verifyOrThrow(rawPassword: String, hashedPassword: String) {
        if (!verifyPassword(rawPassword, hashedPassword)) {
            throw RuntimeException("Invalid password")
        }
    }
}
