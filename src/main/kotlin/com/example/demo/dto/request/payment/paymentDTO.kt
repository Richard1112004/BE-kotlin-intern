package com.example.demo.dto.request.payment

import java.time.LocalDate

data class PaymentDTO (
    val installmentId: Int = 0,
    val status: String = "PENDING",
    val paid_date: LocalDate = LocalDate.now(),
    val due_date: LocalDate = LocalDate.now(),
    val amount: Double = 0.0,
)