package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "CartItems")
data class CartItem (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserModel ?= null,

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    val product: ProductModel ?= null,

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = true)
    val order: OrderModel ?= null,

    @Column(nullable = true)
    val quantity: Int = 1,

    @Column(nullable = true)
    val term : Long = 0,

    @Column(nullable = true)
    val isClear: Boolean = false,
)
