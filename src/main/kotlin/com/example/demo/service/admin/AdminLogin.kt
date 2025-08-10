package com.example.demo.service.admin

import com.example.demo.component.JWTCreateToken
import com.example.demo.component.JWTPasswordAuth
import com.example.demo.dto.request.LoginDTO
import com.example.demo.`interface`.UserLoginStrategy
import com.example.demo.repository.user.UserRepo
import org.springframework.stereotype.Component


@Component("admin_login")
class AdminLogin(
    private val jwtPasswordAuth: JWTPasswordAuth,
    private val jwtCreateToken: JWTCreateToken,
    private val userRepo: UserRepo
) :UserLoginStrategy {

    override fun login(req: LoginDTO): String {
        val admin = userRepo.findByEmail(req.email) ?: throw Exception("Admin not found i")
        jwtPasswordAuth.verifyOrThrow(req.password, admin.password)
        return jwtCreateToken.createJWT(
            admin.id.toString(),
            admin.email,
            "ADMIN",
            3600000,
        )
    }
}