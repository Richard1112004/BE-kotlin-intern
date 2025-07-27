package com.example.demo.controller.admin

import com.example.demo.dto.request.LoginDTO
import com.example.demo.dto.respond.APIRespond
import com.example.demo.service.admin.AdminService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/api/v1/admin")
@RestController
class AdminController (
    private val adminService: AdminService
) {
    @Operation(summary = "Register admins")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully registered admin"),
            ApiResponse(responseCode = "401", description = "Unauthorized")
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
            return ResponseEntity.status(401).body(
                APIRespond(
                    status = 401,
                    message = "Admin registration failed: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }
}