package com.example.demo.repository.orders

import com.example.demo.model.OrderModel
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface OrderRepo : JpaRepository<OrderModel, Long> {
    fun findByUserId(userId: Long): List<OrderModel>
//    fun findByStatus(status: String): List<OrderModel>
//    fun findByOrderDateBetween(startDate: LocalDateTime, endDate: LocalDateTime): List<OrderModel>
}