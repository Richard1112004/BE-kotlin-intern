package com.example.demo.repository.cartItem

import com.example.demo.model.CartItem
import org.springframework.data.jpa.repository.JpaRepository

interface CartItemRepo : JpaRepository<CartItem, Long> {
//    fun findByCartId(cartId: Long): List<CartItem>
//    fun deleteByCartId(cartId: Long)
        fun findAllByUserId(userId: Long): List<CartItem>
}