package com.example.demo.testAPI

import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestAPI {
    @PreAuthorize("hasRole('USER')")
    @ApiResponses(
        value = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User API works"),
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden")
        ]
    )
    @GetMapping("/user")
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello, user API works!")
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(
        value = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "User API works"),
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Forbidden")
        ]
    )
    @GetMapping("/admin")
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello, admin API works!")
    }
}
