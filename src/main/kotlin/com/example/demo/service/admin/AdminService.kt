package com.example.demo.service.admin

import com.example.demo.dto.request.LoginDTO
import com.example.demo.model.Role
import com.example.demo.model.UserModel
import com.example.demo.repository.user.UserRepo
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepo: UserRepo
) {
    fun postAdmin(req: LoginDTO): UserModel {
        val adminModel = UserModel(
            email = req.email,
            password = passwordEncoder.encode(req.password),
            role = Role.ADMIN
        )
        return userRepo.save(adminModel)
    }

}