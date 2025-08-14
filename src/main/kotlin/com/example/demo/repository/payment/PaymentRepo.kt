package com.example.demo.repository.payment

import com.example.demo.model.InstallmentPayment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PaymentRepo : JpaRepository<InstallmentPayment, Long> {
    fun findByInstallmentPlan_IdOrderByDueDateAsc(id: Long): List<InstallmentPayment>
    fun findByStatus(status: String): List<InstallmentPayment>
    @Query("""
        SELECT p from InstallmentPayment p
        JOIN InstallmentPlan ip ON p.installmentPlan.id = ip.id
        JOIN CartItem ci ON ip.cartItem.id = ci.id
        WHERE ci.user.id = :userId
        """)

    fun findAllByUserId(@Param("userId") userId: Long): List<InstallmentPayment>

    @Query("""
        SELECT p from InstallmentPayment p
        JOIN InstallmentPlan ip ON p.installmentPlan.id = ip.id
        JOIN CartItem ci ON ip.cartItem.id = ci.id
        WHERE ci.product.id = :productId
        """)
    fun findByProductId(@Param("productId") productId: Long): List<InstallmentPayment>
}