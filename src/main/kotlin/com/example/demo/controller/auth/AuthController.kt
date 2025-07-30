package com.example.demo.controller.auth

import com.example.demo.dto.request.LoginDTO
import com.example.demo.dto.respond.APIRespond
import com.example.demo.service.admin.AdminLogin
import com.example.demo.service.auth.AuthService
import com.example.demo.service.user.UserLogin
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/auth")
@Tag(name = "Auth", description = "API for authentication operations")
class AuthController (
    private val authService: AuthService,
    ) {
    @Operation(summary = "Login user",
        description = "This endpoint allows users to log in. " +
                "The request body should contain the user's login credentials." +
        "We will return a JWT token if the login is successful."
    )
    @ApiResponses(
        value = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User logged in successfully"),
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
        ]
    )
    @PostMapping("user/login")
    fun loginUser(@RequestBody req : LoginDTO): ResponseEntity<APIRespond<String>> {
        try {
            return ResponseEntity.ok(
                APIRespond<String>(
                    status = 200,
                    data = authService.login("user_login",req),
                    message = "User logged in successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond<String>(
                    status = 400,
                    data = null,
                    message = "An error occurred during login: ${e.message}"
                )
            )
        }
    }

    @Operation(summary = "Login admin",
        description = "This endpoint allows admins to log in. " +
                "The request body should contain the admin's login credentials." +
        "We will return a JWT token if the login is successful."
    )
    @ApiResponses(
        value = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Admin logged in successfully"),
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
        ]
    )
    @PostMapping("admin/login")
    fun loginAdmin(@RequestBody req : LoginDTO): ResponseEntity<APIRespond<String>> {
        try {
            // Assuming you have a similar service for admin login
            return ResponseEntity.ok(
                APIRespond<String>(
                    status = 200,
                    data = authService.login("admin_login",req), // Replace with actual admin login logic
                    message = "Admin logged in successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond<String>(
                    status = 400,
                    data = null,
                    message = "An error occurred during admin login: ${e.message}"
                )
            )
        }
    }

}