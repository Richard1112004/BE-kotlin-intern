package com.example.demo.repository.payment

import com.example.demo.model.InstallmentPayment
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepo : JpaRepository<InstallmentPayment, Long> {
//    fun findByOrderId(orderId: Long): List<InstallmentPayment>
//    fun findByUserId(userId: Long): List<InstallmentPayment>
    fun findByStatus(status: String): List<InstallmentPayment>
}