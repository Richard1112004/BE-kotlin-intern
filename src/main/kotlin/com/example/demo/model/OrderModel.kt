package com.example.demo.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class OrderModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserModel? = null,


    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val total : Double = 0.0,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: Status = Status.PENDING,

)

enum class Status {
    PENDING, SHIPPED, DELIVERED, CANCELLED
}