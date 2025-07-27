package com.example.demo.service.user

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
}