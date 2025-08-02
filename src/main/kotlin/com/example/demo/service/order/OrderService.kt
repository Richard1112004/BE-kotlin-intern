package com.example.demo.service.order

import com.example.demo.dto.request.order.OrderDTO
import com.example.demo.model.OrderModel
import com.example.demo.repository.orders.OrderRepo
import com.example.demo.repository.user.UserRepo
import org.springframework.stereotype.Service


@Service
class OrderService (
    private val orderRepo: OrderRepo,
    private val userRepo: UserRepo
) {
    fun getAllOrders(): List<OrderModel> {
        return orderRepo.findAll()
    }

    fun getOrderById(id: Long): OrderModel {
        return orderRepo.findById(id).orElseThrow { IllegalArgumentException("Order not found with id: $id") }
    }

    fun createOrder(order: OrderDTO): OrderModel {
        val newOrder = OrderModel(
            user = userRepo.findById(order.user_id).orElseThrow{ IllegalArgumentException("User not found with id: ${order.user_id}") },
            total = order.total,
            status = order.status,
            createdAt = java.time.LocalDateTime.now(),
        )
        return orderRepo.save(newOrder)
    }
}