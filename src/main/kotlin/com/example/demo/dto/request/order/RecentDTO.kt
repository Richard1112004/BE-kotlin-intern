package com.example.demo.dto.request.order

data class RecentDTO (
    val orderId: Long = 0,
    val customerEmail: String = "Hung",
    val status: String = "PENDING",
)