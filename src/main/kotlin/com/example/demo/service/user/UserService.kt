package com.example.demo.service.user

import com.example.demo.component.JWTCreateToken
import com.example.demo.dto.request.GGSignInReq
import com.example.demo.dto.request.LoginDTO
import com.example.demo.dto.request.RegisterUser
import com.example.demo.model.user.UserModel
import com.example.demo.repository.user.UserRepo
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepo: UserRepo,
    private val passwordEncoder: PasswordEncoder,
    private val jwtCreateToken: JWTCreateToken
) {
    fun postUser(req: RegisterUser) : UserModel {
        val userModel = UserModel(
            password = passwordEncoder.encode(req.password),
            email = req.email,
            phone = req.phone,
            role = "USER",
        )
        return userRepo.save(userModel)
    }
    fun putPassword(req: LoginDTO): UserModel {
        val user = userRepo.findByEmail(req.email)
            ?: throw IllegalArgumentException("User not found with email: ${req.email}")

        user.password = passwordEncoder.encode(req.password)
        return userRepo.save(user)
    }
    private val verifier: GoogleIdTokenVerifier = GoogleIdTokenVerifier.Builder(
        NetHttpTransport(),
        JacksonFactory.getDefaultInstance()
    )
        .setAudience(listOf("535075964259-1vh7m87v500o26rfm8jthkbhgt1pgvc8.apps.googleusercontent.com",
            "535075964259-vcbjubpgofu8jpp9rbl77uhbhb7nvivd.apps.googleusercontent.com"))
        .build()

    fun signInWithGoogle(idToken: String): String {
        val token = verifier.verify(idToken)
        val payload = token?.payload
            ?: throw IllegalArgumentException("Invalid ID token")
        val email = payload.email
        val user = userRepo.findByEmail(email) ?: run {
            val userModel = UserModel(
                email = email,
                name = payload["name"] as String?,
                role = "USER",
            )
            userRepo.save(userModel)
        }
        return jwtCreateToken.createJWT(
            user.id.toString(),
            user.email!!,
            "USER",
            3600000 // 1 hour
        )
    }
}