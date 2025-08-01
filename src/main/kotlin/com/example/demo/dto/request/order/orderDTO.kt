package com.example.demo.dto.request.order

data class OrderDTO (
    val status: String = "PENDING",
    val total: Double,
    val user_id: Long = 0,
)