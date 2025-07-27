package com.example.demo.`interface`

import com.example.demo.dto.request.LoginDTO

interface UserLoginStrategy {
    fun login(req: LoginDTO): String
}