package com.example.demo.controller.orders

import com.example.demo.dto.request.order.*
import com.example.demo.dto.request.product.ProductDTO
import com.example.demo.dto.respond.APIRespond
import com.example.demo.model.OrderModel
import com.example.demo.service.order.OrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/v1/orders")
@RestController
@Tag(name = "Orders", description = "API for order operations")
class OrdersController (
    private val orderService: OrderService
) {
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all orders")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved all orders"),
            ApiResponse(responseCode = "400", description = "Failed to retrieve orders"),
        ]
    )
    @GetMapping("/all/{userId}")
    fun getAllOrders(@PathVariable userId: Long): ResponseEntity<APIRespond<List<OrderModel>>> {
        try {
            // Simulate fetching all orders
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = orderService.getAllOrders(userId),
                    message = "All orders retrieved successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to retrieve orders: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Post order ")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully posted order"),
            ApiResponse(responseCode = "400", description = "Failed to post order"),
        ]
    )
    @PostMapping
    fun postOrder(@RequestBody order: OrderDTO): ResponseEntity<APIRespond<OrderModel>> {
        try {

            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = orderService.createOrder(order),
                    message = "Order posted successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to post order: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Summarize all orders")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully summarized all orders"),
            ApiResponse(responseCode = "400", description = "Failed to summarize orders"),
        ]
    )
    @GetMapping("/summarize")
    fun summarizeOrders(): ResponseEntity<APIRespond<Long>> {
        try {
            // Simulate summarizing all orders
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = orderService.summarizeOrders(),
                    message = "All orders summarized successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to summarize orders: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Summarizes order today's orders")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully summarized today's orders"),
            ApiResponse(responseCode = "400", description = "Failed to summarize today's orders"),
        ]
    )
    @GetMapping("/today")
    fun summarizeTodayOrders(): ResponseEntity<APIRespond<Long>> {
        try {
            // Simulate summarizing today's orders
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = orderService.summarizeTodayOrders(),
                    message = "Today's orders summarized successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to summarize today's orders: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get 3 recent orders")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved recent orders"),
            ApiResponse(responseCode = "400", description = "Failed to retrieve recent orders"),
        ]
    )
    @GetMapping("/recent")
    fun getRecentOrders(): ResponseEntity<APIRespond<List<RecentDTO>>> {
        try {
            // Simulate fetching recent orders
            val recentOrders = orderService.getRecentOrders(); // Replace with actual user ID logic
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = recentOrders, // Assuming we want the 3 most recent orders
                    message = "Recent orders retrieved successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to retrieve recent orders: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all order and user details")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved all order and user details"),
            ApiResponse(responseCode = "400", description = "Failed to retrieve order and user details"),
        ]
    )
    @GetMapping("/all-details")
    fun getAllOrderAndUserDetails(): ResponseEntity<APIRespond<List<AllDetailOrderDTO>>> {
        try {
            // Simulate fetching all order and user details
            val orderDetails = orderService.getAllOrdersAndUsers();
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = orderDetails,
                    message = "All order and user details retrieved successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to retrieve order and user details: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Change order status")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully changed order status"),
            ApiResponse(responseCode = "400", description = "Failed to change order status"),
        ]
    )
    @PutMapping("/{orderId}/status")
    fun changeOrderStatus(
        @PathVariable orderId: Long,
        @RequestParam status: String
    ): ResponseEntity<APIRespond<Void>> {
        return try {
            orderService.updateOrderStatus(
                orderId = orderId,
                status = status
            );
            ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    message = "Order status changed successfully"
                )
            )
        } catch (e: Exception) {
            ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to change order status: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all detail about order")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved all order details"),
            ApiResponse(responseCode = "400", description = "Failed to retrieve order details"),
        ]
    )
    @GetMapping("/order-details/{orderId}")
    fun getOrderDetails(@PathVariable orderId: Long): ResponseEntity<APIRespond<OrderDetailDTO>> {
        return try {
            ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = orderService.findOrderFullInfoById(orderId),
                    message = "Order details retrieved successfully"
                )
            )
        } catch (e: Exception) {
            ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to retrieve order details: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all products in order")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved all products in order"),
            ApiResponse(responseCode = "400", description = "Failed to retrieve products in order"),
        ]
    )
    @GetMapping("/products/{orderId}")
    fun getProductsInOrder(@PathVariable orderId: Long): ResponseEntity<APIRespond<List<ProductDTO>>> {
        return try {
            ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = orderService.getOrderProducts(orderId),
                    message = "Products in order retrieved successfully"
                )
            )
        } catch (e: Exception) {
            ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to retrieve products in order: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

}

