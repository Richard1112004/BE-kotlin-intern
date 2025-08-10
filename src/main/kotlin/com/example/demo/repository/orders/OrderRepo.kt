package com.example.demo.repository.orders

import com.example.demo.dto.request.order.AllDetailOrderDTO
import com.example.demo.dto.request.order.OrderDetailDTO
import com.example.demo.dto.request.order.RecentDTO
import com.example.demo.dto.request.product.ProductDTO
import com.example.demo.model.CartItem
import com.example.demo.model.OrderModel
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.All

interface OrderRepo : JpaRepository<OrderModel, Long> {
    fun findByUserId(userId: Long): List<OrderModel>
//    fun findByStatus(status: String): List<OrderModel>
//    fun findByOrderDateBetween(startDate: LocalDateTime, endDate: LocalDateTime): List<OrderModel>
    fun findAllByUserId(userId: Long): List<OrderModel>
    fun countByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): Long
    @Query("""
    SELECT new com.example.demo.dto.request.order.RecentDTO(o.id, u.email, o.status)
    FROM OrderModel o
    JOIN o.user u
""")
    fun findOrdersWithUserDetails(pageable: Pageable): List<RecentDTO>


    @Query("""
    SELECT new com.example.demo.dto.request.order.AllDetailOrderDTO(
        o.id,
        o.createdAt,
        o.total,
        o.status,
        u.email
    )
    FROM OrderModel o
    JOIN o.user u
""")
    fun findAllOrdersAndUsers(): List<AllDetailOrderDTO>

    @Query("""
        SELECT new com.example.demo.dto.request.order.OrderDetailDTO(
            o.id,
            o.status,
            u.name,
            u.email,
            u.phone,
            u.province || ", " || u.street)
        FROM OrderModel o
        JOIN o.user u
        WHERE o.id = :orderId
    """)
    fun findOrderFullInfoById(orderId: Long): OrderDetailDTO

    @Query("""
        SELECT new com.example.demo.dto.request.product.ProductDTO(
            p.name,
            p.description,
            p.price,
            p.quantity,
            p.image
        )
        FROM ProductModel p
        JOIN CartItem c ON p.id = c.product.id
        JOIN OrderModel o ON c.order.id = o.id
        WHERE o.id = :orderId
    """)
    fun findProductsByOrderId(orderId: Long): List<ProductDTO>
}