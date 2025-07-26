package com.example.demo.controller

import com.example.demo.dto.request.LoginUser
import com.example.demo.dto.respond.APIRespond
import com.example.demo.service.UserLogin
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/auth")
class AuthController (
    private val userLogin: UserLogin

) {
    @PostMapping("user/login")
    fun loginUser(@RequestBody req : LoginUser): ResponseEntity<APIRespond<String>> {
        try {
            return ResponseEntity.ok(
                APIRespond<String>(
                    status = 200,
                    data = userLogin.login(req),
                    message = "User logged in successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(
                APIRespond<String>(
                    status = 401,
                    data = null,
                    message = "An error occurred during login: ${e.message}"
                )
            )
        }
    }
}