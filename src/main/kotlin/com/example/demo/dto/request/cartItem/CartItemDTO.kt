package com.example.demo.dto.request.cartItem

import com.fasterxml.jackson.annotation.JsonProperty

data class CartItemDTO (
    val productId: Long ?= 0,
    val quantity: Int ?= 1,
    val term: Int ?= 0,
    val orderId: Long ?= 0,
    val userId: Long ?= 0,
    val clear: Boolean ?= false
)