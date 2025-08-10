package com.example.demo.service.user

import com.example.demo.component.JWTCreateToken
import com.example.demo.dto.request.*
import com.example.demo.dto.request.user.UserDTO
import com.example.demo.repository.user.UserRepo
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.firebase.auth.FirebaseAuth
import com.example.demo.model.UserModel
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.stereotype.Service
import org.springframework.security.oauth2.jwt.Jwt



@Service
class UserService(
    private val userRepo: UserRepo,
    private val passwordEncoder: PasswordEncoder,
    private val jwtCreateToken: JWTCreateToken,
    private val jwtDecoder: JwtDecoder
) {
    fun postUser(req: RegisterUser) : UserModel {
        val userModel = UserModel(
            password = passwordEncoder.encode(req.password),
            email = req.email,
            phone = req.phone,
        )
        return userRepo.save(userModel)
    }
    fun FindByEmail(email: String): UserModel {
        return userRepo.findByEmail(email) ?: throw IllegalArgumentException("User not found with email: $email")
    }
    fun putPassword(req: ResetPasswordDTO) {
        val decodedJwt: Jwt = jwtDecoder.decode(req.token)

        val scope = decodedJwt.claims["scope"]?.toString()
        if (scope != "RESET-PASSWORD") {
            throw IllegalArgumentException("Invalid token scope")
        }

        val email = decodedJwt.subject
        val encodedPassword = passwordEncoder.encode(req.newPassword)

        val user = userRepo.findByEmail(email)
            ?: throw IllegalArgumentException("User not found")
        user.password = encodedPassword
        userRepo.save(user)
        return
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
                name = payload["name"] as String,
            )
            userRepo.save(userModel)
        }
        return jwtCreateToken.createJWT(
            user.id.toString(),
            user.email,
            "USER",
            3600000 // 1 hour
        )
    }

    fun verifyIdTokenEmail(req: FireBaseUserDTO): String {
        val decodedToken = FirebaseAuth.getInstance().verifyIdToken(req.idToken)
        val userModel = UserModel(
            email = decodedToken.email ?: throw IllegalArgumentException("Email not found in token"),
            phone = req.phone ?: "",
            password = passwordEncoder.encode(req.password),
        )
        userRepo.save(userModel)
        return jwtCreateToken.createJWT(
            decodedToken.uid,
            decodedToken.email ?: throw IllegalArgumentException("Email not found in token"),
            "USER",
            3600000 // 1 hour
        )
    }

    fun updateUserProfile(userProfileDTO: UserProfileDTO, id: Long): UserModel {
        val existingUser = userRepo.findById(id)
            .orElseThrow { IllegalArgumentException("User not found with id: $id") }

        val updatedUser = existingUser.copy(
            name = userProfileDTO.name ?: existingUser.name,
            phone = userProfileDTO.phone ?: existingUser.phone,
            province = userProfileDTO.province ?: existingUser.province,
            street = userProfileDTO.street ?: existingUser.street
        )

        return userRepo.save(updatedUser)
    }

    fun getAllUsers(): List<UserDTO> {
        return userRepo.findAllUserDTO();
    }

    fun getUserById(id: Long): UserModel {
        return userRepo.findById(id)
            .orElseThrow { IllegalArgumentException("User not found with id: $id") }
    }
}