package com.example.demo.controller

import com.example.demo.dto.request.register
import com.example.demo.dto.respond.APIRespond
import com.example.demo.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/api/v1/user")
@RestController
class UserController (
    private val userService: UserService
) {
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Register users")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully registered user"),
            ApiResponse(responseCode = "401", description = "Unauthorized")
        ]
    )
    @PostMapping("/register")
    fun postUser(@RequestBody req: register): ResponseEntity<APIRespond<Void>> {
        try {
            userService.postUser(req)
            return ResponseEntity.ok(
                APIRespond<Void>(
                    status = 200,
                    data = null,
                    message = "register successful"
                ) //test
            )
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(
                APIRespond<Void>(
                    status = 401,
                    data = null,
                    message = e.message ?: "Unauthorized"
                )
            )
        }
    }
}