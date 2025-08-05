package com.example.demo.service.cartItem

import com.example.demo.dto.request.cartItem.CartItemDTO
import com.example.demo.model.CartItem
import com.example.demo.repository.cartItem.CartItemRepo
import com.example.demo.repository.orders.OrderRepo
import com.example.demo.repository.product.ProductRepo
import com.example.demo.repository.user.UserRepo
import org.springframework.stereotype.Service


@Service
class CartItemService (
    private val cartItemRepo: CartItemRepo,
    private val productRepo: ProductRepo,
    private val orderRepo: OrderRepo,
     private val userRepo: UserRepo
) {
    // Add methods to handle cart item operations, e.g., add, update, delete, etc.
    // For example:
    fun getAllCartItems(userId: Long): List<CartItem> {
        return cartItemRepo.findAllByUserId(userId);
    }

    fun addCartItem(cartItem: CartItemDTO): CartItem {
        val newCartItem = CartItem(
            product = productRepo.findById(cartItem.productId!!).orElseThrow({ IllegalArgumentException("Product not found with id: ${cartItem.productId}") }),
            quantity = cartItem.quantity!!,
            term = cartItem.term?.toLong() ?: 0L,
            order = if(cartItem.orderId!=null){
                orderRepo.findById(cartItem.orderId).orElseThrow{ IllegalArgumentException("Order not found with id: ${cartItem.orderId}") }
            }
                    else null,

            user = userRepo.findById(cartItem.userId!!).orElseThrow({ IllegalArgumentException("User not found with id: ${cartItem.userId}") }),
            clear = cartItem.clear ?: false
        )
        return cartItemRepo.save(newCartItem)
    }
    fun updateCartItem(cartItem: CartItemDTO, id: Long): CartItem {
        val existingCartItem = cartItemRepo.findById(id)
            .orElseThrow { IllegalArgumentException("Cart item not found with id: $id") }

        val updatedCartItem = existingCartItem.copy(
            quantity = cartItem.quantity ?: existingCartItem.quantity,
            term = cartItem.term?.toLong() ?: existingCartItem.term,
            clear = cartItem.clear ?: existingCartItem.clear,
            user = cartItem.userId?.let { userRepo.findById(it).orElse(existingCartItem.user) }
                ?: existingCartItem.user,
            product = cartItem.productId?.let { productRepo.findById(it).orElse(existingCartItem.product) }
                ?: existingCartItem.product,
            order = cartItem.orderId?.let { orderRepo.findById(it).orElse(existingCartItem.order) }
                ?: existingCartItem.order
        )

        return cartItemRepo.save(updatedCartItem)
    }


    fun deleteCartItem(id: Long) {
        cartItemRepo.deleteById(id)
    }
}