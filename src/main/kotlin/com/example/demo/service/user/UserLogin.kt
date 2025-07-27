package com.example.demo.service.user

import com.example.demo.component.JWTCreateToken
import com.example.demo.component.JWTPasswordAuth
import com.example.demo.dto.request.LoginDTO
import com.example.demo.repository.user.UserRepo
import org.springframework.stereotype.Component


@Component("user_login")
class UserLogin (
    private val userRepo: UserRepo,
    private val jwtPasswordAuth: JWTPasswordAuth,
    private val jwtCreateToken: JWTCreateToken
) {
    fun login(req: LoginDTO) : String{
        val user = userRepo.findByEmail(req.email) ?: throw Exception("User not found")
        jwtPasswordAuth.verifyOrThrow(req.password,
            user.password!!
        )
        return jwtCreateToken.createJWT(
            user.id.toString(),
            user.email!!,
            user.role!!,
            3600000,
        )
    }
}