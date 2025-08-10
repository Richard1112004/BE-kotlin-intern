package com.example.demo.dto.request.order

data class OrderDetailDTO(
    val orderId: Long,
    val status: String,
    val customerName: String,
    val customerEmail: String,
    val customerPhone: String,
    val shippingAddress: String,
)