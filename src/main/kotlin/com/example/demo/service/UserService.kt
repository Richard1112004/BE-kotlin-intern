package com.example.demo.service

import com.example.demo.dto.request.register
import com.example.demo.model.UserModel
import com.example.demo.repository.UserRepo
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepo: UserRepo
) {
    fun postUser(req: register) : UserModel {
        val userModel = UserModel(
            password = req.password,
            email = req.email,
            phone = req.phone,
            role = "USER",
        )
        return userRepo.save(userModel)
    }
}