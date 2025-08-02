package com.example.demo.repository.product

import com.example.demo.model.ProductModel
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepo : JpaRepository<ProductModel, Long> {
    fun findByName(name: String): ProductModel?
    fun findByPriceBetween(minPrice: Double, maxPrice: Double): List<ProductModel>
}