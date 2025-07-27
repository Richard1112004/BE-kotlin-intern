package com.example.demo.controller.auth

import com.example.demo.dto.request.LoginDTO
import com.example.demo.dto.respond.APIRespond
import com.example.demo.service.admin.AdminLogin
import com.example.demo.service.user.UserLogin
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/auth")
class AuthController (
    private val userLogin: UserLogin,
    private val adminLogin: AdminLogin,
    ) {
    @Operation(summary = "Login user")
    @ApiResponses(
        value = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User logged in successfully"),
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
        ]
    )
    @PostMapping("user/login")
    fun loginUser(@RequestBody req : LoginDTO): ResponseEntity<APIRespond<String>> {
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

    @Operation(summary = "Login admin")
    @ApiResponses(
        value = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Admin logged in successfully"),
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
        ]
    )
    @PostMapping("admin/login")
    fun loginAdmin(@RequestBody req : LoginDTO): ResponseEntity<APIRespond<String>> {
        try {
            // Assuming you have a similar service for admin login
            return ResponseEntity.ok(
                APIRespond<String>(
                    status = 200,
                    data = adminLogin.login(req), // Replace with actual admin login logic
                    message = "Admin logged in successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(
                APIRespond<String>(
                    status = 401,
                    data = null,
                    message = "An error occurred during admin login: ${e.message}"
                )
            )
        }
    }

}