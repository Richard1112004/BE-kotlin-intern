package com.example.demo.repository.installment

import com.example.demo.model.InstallmentPayment
import com.example.demo.model.InstallmentPlan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface InstallmentRepo : JpaRepository<InstallmentPlan, Long> {
//    fun findByCartId(cartId: Long): List<InstallmentPlan>
//    fun deleteByCartId(cartId: Long)
@Query("""
    SELECT ip FROM InstallmentPlan ip 
    JOIN CartItem ci ON ip.cartItem.id = ci.id 
    WHERE ci.user.id = :userId
""")
fun findAllByUserId(@Param("userId") userId: Long): List<InstallmentPlan>


}