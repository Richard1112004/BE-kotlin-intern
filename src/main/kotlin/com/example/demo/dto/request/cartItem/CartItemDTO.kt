package com.example.demo.dto.request.cartItem

import com.fasterxml.jackson.annotation.JsonProperty

data class CartItemDTO (
    val productId: Long ?= null,
    val quantity: Int ?= null,
    val term: Int ?= null,
    val orderId: Long ?= null,
    val userId: Long ?= null,
    val clear: Boolean ?= null
)