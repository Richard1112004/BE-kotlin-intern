package com.example.demo.repository.installment

import com.example.demo.model.InstallmentPlan
import org.springframework.data.jpa.repository.JpaRepository

interface InstallmentRepo : JpaRepository<InstallmentPlan, Long> {
//    fun findByCartId(cartId: Long): List<InstallmentPlan>
//    fun deleteByCartId(cartId: Long)
}