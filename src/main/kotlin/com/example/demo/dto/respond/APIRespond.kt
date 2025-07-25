package com.example.demo.dto.respond

data class APIRespond<T>(
    val status: Int,
    val message: String,
    val data: T?
)
