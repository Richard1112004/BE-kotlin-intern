package com.example.demo.dto.request.cartItem

data class CartItemDTO (
    val productId: Long,
    val quantity: Int ?= 1,
    val price: Double,
    val term: Int ?= 0,
    val orderId: Long ?= 0,
    val userId: Long ?= 0,
    val isClear: Boolean ?= false
)