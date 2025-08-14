package com.example.demo.dto.request.product

data class ProductDTO (
    val name: String,
    val description: String,
    val price: Double,
    val quality: Long = 0,
    val images: String = "",
    val installmentId: Long? = null,
)