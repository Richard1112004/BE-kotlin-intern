package com.example.demo.controller.user

import com.example.demo.dto.request.*
import com.example.demo.dto.respond.APIRespond
import com.example.demo.service.user.UserService
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/api/v1/user")
@RestController
@Tag(name = "User", description = "API for user operations")
class UserController (
    private val userService: UserService
) {
    @Operation(summary = "Check email availability",
        description = ("This endpoint checks if the provided email is available for registration. " +
                "If the email is already taken, it will return an error message.")
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Email is available"),
            ApiResponse(responseCode = "400", description = "Email is already taken")
        ]
    )
    @PostMapping("/check-email")
    fun FindByEmail(@RequestBody req: EmailDTO) : ResponseEntity<APIRespond<Void>>{
        try{
            userService.FindByEmail(req.email)
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    message = "Email is available"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Email is already taken: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }


    @Operation(summary  = "Edit password",
        description = "This endpoint allows users to change their password. " +
                "The request body should contain the JWT token, new password" +
                "We will return a success message if the password is updated successfully."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully edited password"),
            ApiResponse(responseCode = "400", description = "Bad Request"),
        ]
    )
    @PutMapping("/password")
    fun putPassword(
        @RequestBody req: ResetPasswordDTO
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


    @Operation(summary = "Sign in with Google",
        description = "This endpoint allows users to sign in using their Google account.\n" +
                "The request body should contain the Google ID token get from Google.\n" +
        "We will return JWT token if the sign in is successful.\n"
    )
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

    @Operation(summary = "Register with Firebase",
        description = "This endpoint allows users to register using Firebase. " +
                "The request body should contain the Firebase ID token get from Firebase, phone number, and password."+
        "We will return JWT token if the registration is successful."
    )
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