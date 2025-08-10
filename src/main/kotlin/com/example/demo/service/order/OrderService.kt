package com.example.demo.service.order

import com.example.demo.dto.request.order.*
import com.example.demo.dto.request.product.ProductDTO
import com.example.demo.model.OrderModel
import com.example.demo.repository.orders.OrderRepo
import com.example.demo.repository.user.UserRepo
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.All


@Service
class OrderService (
    private val orderRepo: OrderRepo,
    private val userRepo: UserRepo
) {
    fun getAllOrders(userId: Long): List<OrderModel> {
        return orderRepo.findAllByUserId(userId);
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

    fun summarizeOrders() : Long{
        return orderRepo.count()
    }
    fun summarizeTodayOrders(): Long {
        val today = java.time.LocalDate.now()
        return orderRepo.countByCreatedAtBetween(
            java.time.LocalDateTime.of(today, java.time.LocalTime.MIDNIGHT),
            java.time.LocalDateTime.of(today, java.time.LocalTime.now())
        )
    }

    fun getRecentOrders(): List<RecentDTO> {
        return orderRepo.findOrdersWithUserDetails(PageRequest.of(0, 3));
    }

    fun getAllOrdersAndUsers(): List<AllDetailOrderDTO> {
        return orderRepo.findAllOrdersAndUsers();
    }

    fun updateOrderStatus(orderId: Long, status: String): OrderModel {
        val order = orderRepo.findById(orderId).orElseThrow { IllegalArgumentException("Order not found with id: $orderId") }
        order.status = status
        return orderRepo.save(order)
    }

    fun findOrderFullInfoById(orderId: Long): OrderDetailDTO {
        return orderRepo.findOrderFullInfoById(orderId);
    }

    fun getOrderProducts(orderId: Long): List<ProductDTO> {
        return orderRepo.findProductsByOrderId(orderId);
    }


}