package com.example.demo.repository.payment

import com.example.demo.model.InstallmentPayment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepo : JpaRepository<InstallmentPayment, Long> {
    fun findByInstallmentPlan_IdOrderByDueDateAsc(id: Long): List<InstallmentPayment>
    fun findByStatus(status: String): List<InstallmentPayment>
}