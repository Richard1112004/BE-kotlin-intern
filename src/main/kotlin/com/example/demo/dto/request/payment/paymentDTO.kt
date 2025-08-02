package com.example.demo.dto.request.payment

import java.time.LocalDate

data class PaymentDTO (
    val installmentId: Long ?= null,
    val status: String ?= null,
    val paid_date: LocalDate ?= null,
    val due_date: LocalDate ?= null,
    val amount: Double ?= null,
)