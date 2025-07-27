package com.example.demo.controller.user

import com.example.demo.dto.request.RegisterUser
import com.example.demo.dto.respond.APIRespond
import com.example.demo.service.user.UserService
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
            ApiResponse(responseCode = "400", description = "Bad Request"),
        ]
    )
    @PostMapping("/register")
    fun postUser(@RequestBody req: RegisterUser): ResponseEntity<APIRespond<Void>> {
        try {
            userService.postUser(req)
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    message = "register successful"
                ) //test
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "register failed: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }
}