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
        description = "This endpoint allows regular users to authenticate and log into the system. " +
                "The request body should contain valid user credentials including email/username and password. " +
                "Upon successful authentication, the system validates the credentials against the user database " +
                "and returns a JWT token that can be used for subsequent authenticated requests. " +
                "This token contains user information and permissions for the USER role."
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
        description = "This endpoint allows administrators to authenticate and log into the system with elevated privileges. " +
                "The request body should contain valid admin credentials including email/username and password. " +
                "Upon successful authentication, the system validates the admin credentials against the admin database " +
                "and returns a JWT token with ADMIN role permissions. This token provides access to administrative " +
                "functions and management operations that regular users cannot access."
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