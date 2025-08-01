package com.example.demo.dto.request

data class ResetPasswordDTO(
    val newPassword: String,
    val token: String
)