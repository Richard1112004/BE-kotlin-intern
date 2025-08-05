package com.example.demo.controller.cartItem

import com.example.demo.dto.request.cartItem.CartItemDTO
import com.example.demo.dto.respond.APIRespond
import com.example.demo.model.CartItem
import com.example.demo.service.cartItem.CartItemService
import com.example.demo.service.user.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/v1/cart-item")
@RestController
@Tag(name = "Cart-Item", description = "API for cart item operations")
class CartItemController(
    private val cartItemService: CartItemService,
    private val userService: UserService
) {

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Post new cart items")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully added cart item"),
            ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @PostMapping
    fun postCartItem(@RequestBody req: CartItemDTO): ResponseEntity<APIRespond<CartItem>> {
        try {
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = cartItemService.addCartItem(req),
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

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Update cart items")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully updated cart item"),
            ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @PutMapping("{id}")
    fun updateCartItem(@RequestBody req: CartItemDTO, @PathVariable id: Long): ResponseEntity<APIRespond<Void>> {
        try {
            cartItemService.updateCartItem(req, id)
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

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Delete cart items")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully deleted cart item"),
            ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteCartItem(@PathVariable id: Long): ResponseEntity<APIRespond<Void>> {
        try {
            cartItemService.deleteCartItem(id)
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

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all cart items")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved cart items"),
            ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @GetMapping("/all/{userId}")
    fun getAllCartItems(@PathVariable userId: Long): ResponseEntity<APIRespond<List<CartItem>>> {
        try {

            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = cartItemService.getAllCartItems(userId),
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
