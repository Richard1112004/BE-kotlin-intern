package com.example.demo.service.admin

import com.example.demo.component.JWTCreateToken
import com.example.demo.component.JWTPasswordAuth
import com.example.demo.dto.request.LoginDTO
import com.example.demo.repository.admin.AdminRepo
import org.springframework.stereotype.Component


@Component("admin_login")
class AdminLogin (
    private val adminRepo: AdminRepo,
    private val jwtPasswordAuth: JWTPasswordAuth,
    private val jwtCreateToken: JWTCreateToken
) {
    fun login(req: LoginDTO): String {
        val admin = adminRepo.findByEmail(req.email) ?: throw Exception("Admin not found")
        jwtPasswordAuth.verifyOrThrow(req.password, admin.password!!)
        return jwtCreateToken.createJWT(
            admin.id.toString(),
            admin.email!!,
            "ADMIN",
            3600000,
        )
    }
}