package com.example.demo.controller.admin

import com.example.demo.dto.request.LoginDTO
import com.example.demo.dto.respond.APIRespond
import com.example.demo.service.admin.AdminService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/api/v1/admin")
@RestController
@Tag(name = "Admin", description = "API for admin operations")
class AdminController (
    private val adminService: AdminService
) {
    @Operation(summary = "Register admins")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully registered admin"),
            ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @PostMapping("/register")
    fun postAdmin(@RequestBody req: LoginDTO): ResponseEntity<APIRespond<Void>> {
         try {
            adminService.postAdmin(req)
             return ResponseEntity.ok(
                 APIRespond(
                     status = 200,
                     message = "Admin registration successful"
                 )
             )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Admin registration failed: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }
}