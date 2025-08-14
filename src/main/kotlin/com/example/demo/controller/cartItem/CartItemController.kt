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
    @Operation(summary = "Post new cart items",
        description = "This endpoint allows authenticated users to add new items to their shopping cart. " +
                "The request body should contain cart item information including product ID, quantity, " +
                "and any specific product options or variations. Only users with USER role can access this endpoint. " +
                "The system validates product availability and user permissions before adding the item. " +
                "This is essential for the e-commerce shopping experience and order preparation."
    )
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
    @Operation(summary = "Update cart items",
        description = "This endpoint allows authenticated users to modify existing items in their shopping cart. " +
                "The request requires a valid cart item ID as a path parameter and updated item information in the request body. " +
                "Users can update quantities, product options, or other cart item attributes. " +
                "Only users with USER role can access this endpoint, and they can only modify their own cart items. " +
                "This functionality is crucial for allowing customers to adjust their orders before checkout."
    )
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
    @Operation(summary = "Delete cart items",
        description = "This endpoint allows authenticated users to remove specific items from their shopping cart. " +
                "The request requires a valid cart item ID as a path parameter to identify which item to delete. " +
                "Only users with USER role can access this endpoint, and they can only delete their own cart items. " +
                "Once deleted, the item is permanently removed from the cart and cannot be recovered. " +
                "This functionality helps users manage their cart contents and remove unwanted items before checkout."
    )
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
    @Operation(summary = "Get all cart items",
        description = "This endpoint retrieves all items currently in a specific user's shopping cart. " +
                "The request requires a valid user ID as a path parameter to identify whose cart to retrieve. " +
                "Only users with USER role can access this endpoint, ensuring cart privacy and security. " +
                "The response includes detailed information about each cart item including product details, " +
                "quantities, prices, and total values. This endpoint is essential for displaying cart contents " +
                "and calculating order totals before checkout."
    )
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
