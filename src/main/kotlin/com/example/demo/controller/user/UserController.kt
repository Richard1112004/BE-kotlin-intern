package com.example.demo.controller.user

import com.example.demo.dto.request.FireBaseUserDTO
import com.example.demo.dto.request.GGSignInReq
import com.example.demo.dto.request.LoginDTO
import com.example.demo.dto.request.RegisterUser
import com.example.demo.dto.respond.APIRespond
import com.example.demo.service.user.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/api/v1/user")
@RestController
class UserController (
    private val userService: UserService
) {
    @Operation(summary  = "Edit password")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully edited password"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
        ]
    )
    @PutMapping("/password")
    fun putPassword(
        @RequestBody req: LoginDTO
    ): ResponseEntity<APIRespond<Void>> {
        try {
            userService.putPassword(req)
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    message = "password updated successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "password update failed: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }


    @Operation(summary = "Sign in with Google")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully signed in with Google"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
        ]
    )
    @PostMapping("/google")
    fun signInWithGoogle(
        @RequestBody req: GGSignInReq
    ): ResponseEntity<APIRespond<String>> {

        try {
            return ResponseEntity.ok(
                APIRespond<String>(
                    status = 200,
                    data = userService.signInWithGoogle(req.idToken),
                    message = "Sign in with Google successful"
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Sign in with Google failed: ${e.message ?:  e::class.java.simpleName}"
                )
            )
        }
    }

    @Operation(summary = "Register with Firebase")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully register with Firebase"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
        ]
    )
    @PostMapping("/firebase")
    fun signInWithFirebase(
        @RequestBody req: FireBaseUserDTO
    ): ResponseEntity<APIRespond<String>> {
        try {
            return ResponseEntity.ok(
                APIRespond<String>(
                    status = 200,
                    data = userService.verifyIdTokenEmail(req),
                    message = "Register with Firebase successful"
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Register with Firebase failed: ${e.message ?: e::class.java.simpleName}"
                )
            )
        }
    }
}