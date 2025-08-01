package com.example.demo.controller.cartItem

import com.example.demo.dto.request.cartItem.CartItemDTO
import com.example.demo.dto.respond.APIRespond
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/v1/cart-item")
@RestController
@Tag(name = "Cart-Item", description = "API for cart item operations")
class CartItemController {

    @Operation(summary = "Post new cart items")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully added cart item"),
            ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @PostMapping
    fun postCartItem(@RequestBody req: CartItemDTO): ResponseEntity<APIRespond<Void>> {
        try {
            // Simulate adding a cart item
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    message = "Cart item added successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to add cart item: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @Operation(summary = "Update cart items")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully updated cart item"),
            ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @PutMapping
    fun updateCartItem(@RequestBody req: CartItemDTO): ResponseEntity<APIRespond<Void>> {
        try {
            // Simulate updating a cart item
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    message = "Cart item updated successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to update cart item: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @Operation(summary = "Delete cart items")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully deleted cart item"),
            ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteCartItem(@PathVariable id: Int): ResponseEntity<APIRespond<Void>> {
        try {
            // Simulate deleting a cart item
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    message = "Cart item deleted successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to delete cart item: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @Operation(summary = "Get all cart items")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved cart items"),
            ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @GetMapping("/all")
    fun getAllCartItems(): ResponseEntity<APIRespond<Void>> {
        try {
            // Simulate fetching all cart items
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    message = "All cart items retrieved successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to retrieve cart items: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }
}
