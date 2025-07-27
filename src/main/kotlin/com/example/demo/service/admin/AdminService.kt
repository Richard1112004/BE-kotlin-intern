package com.example.demo.service.admin

import com.example.demo.dto.request.LoginDTO
import com.example.demo.model.admin.AdminModel
import com.example.demo.repository.admin.AdminRepo
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminService (
    private val adminRepo: AdminRepo,
    private val passwordEncoder: PasswordEncoder
) {
    fun postAdmin(req: LoginDTO): AdminModel {
        val adminModel = AdminModel(
            email = req.email,
            password = passwordEncoder.encode(req.password),
            role = "ADMIN"
        )
        return adminRepo.save(adminModel)
    }

}