package com.example.demo.dto.request.order

import java.time.LocalDateTime

data class AllDetailOrderDTO (
    val id: Long,
    val createdAt: LocalDateTime,
    val total: Double,
    val status: String,
    val email: String
)