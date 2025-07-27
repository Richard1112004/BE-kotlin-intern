package com.example.demo.service.auth

import com.example.demo.dto.request.LoginDTO
import com.example.demo.`interface`.LoginStrategy
import com.example.demo.`interface`.UserLoginStrategy
import org.springframework.stereotype.Component

@Component
class AuthService (
    private val loginStrategy: LoginStrategy,
) {
    fun login(userType: String,req: LoginDTO): String {
        val strategy = loginStrategy.getStrategy(userType);
        return strategy.login(req);
    }
}