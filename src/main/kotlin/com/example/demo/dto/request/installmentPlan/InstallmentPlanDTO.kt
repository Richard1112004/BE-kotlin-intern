package com.example.demo.dto.request.installmentPlan

import java.time.LocalDate

data class InstallmentPlanDTO (
    val cartItemId: Int = 0,
    val status: String = "PENDING",
    val totalMonth: Double,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val installmentAmount: Double,
    val lateFee: Double = 0.0,
)