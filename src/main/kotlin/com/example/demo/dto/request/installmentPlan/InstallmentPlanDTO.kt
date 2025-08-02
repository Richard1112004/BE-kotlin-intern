package com.example.demo.dto.request.installmentPlan

import java.time.LocalDate

data class InstallmentPlanDTO (
    val cartItemId: Long ?= null,
    val status: String ?= null,
    val totalMonth: Int ?= null,
    val startDate: LocalDate ?= null,
    val endDate: LocalDate ?= null,
    val installmentAmount: Double ?= null,
    val lateFee: Double ?= null,
)