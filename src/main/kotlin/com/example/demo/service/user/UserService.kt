package com.example.demo.service.user

import com.example.demo.dto.request.LoginDTO
import com.example.demo.dto.request.RegisterUser
import com.example.demo.model.user.UserModel
import com.example.demo.repository.user.UserRepo
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepo: UserRepo,
    private val passwordEncoder: PasswordEncoder,
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
}