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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(summary = "Get all orders",
        description = "This endpoint retrieves all orders associated with a specific user ID. " +
                "The request requires a valid user ID as a path parameter to identify whose orders to retrieve. " +
                "Both users with USER role (for their own orders) and administrators with ADMIN role " +
                "(for any user's orders) can access this endpoint. The response includes comprehensive order information " +
                "such as order ID, status, items, total amount, timestamps, and delivery details. " +
                "This endpoint is essential for order history, tracking, and customer service operations."
    )
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
    @Operation(summary = "Post order",
        description = "This endpoint allows authenticated users to create and submit a new order. " +
                "The request body should contain complete order information including selected items, " +
                "quantities, delivery address, payment method, and any special instructions. " +
                "Only users with USER role can access this endpoint. The system validates all order details, " +
                "checks product availability, calculates totals, and processes the order through the system. " +
                "Upon successful creation, the order is assigned a unique ID and enters the fulfillment pipeline."
    )
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
    @Operation(summary = "Summarize all orders",
        description = "This endpoint provides administrators with a comprehensive summary and analytics of all orders in the system. " +
                "Only users with ADMIN role can access this endpoint due to its sensitive business intelligence nature. " +
                "The response includes aggregated data such as total number of orders, total revenue, " +
                "order status distributions, popular products, and other key business metrics. " +
                "This endpoint is crucial for business reporting, analytics, and strategic decision-making purposes."
    )
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
    @Operation(summary = "Summarizes order today's orders",
        description = "This endpoint provides administrators with a comprehensive summary and analytics of all orders placed today. " +
                "Only users with ADMIN role can access this endpoint due to its business intelligence nature. " +
                "The response includes daily metrics such as total orders count, revenue generated today, " +
                "average order values, and comparison with previous days. This endpoint is essential for daily " +
                "business monitoring, performance tracking, and real-time operational insights."
    )
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
    @Operation(summary = "Get 3 recent orders",
        description = "This endpoint allows administrators to retrieve the three most recently placed orders in the system. " +
                "Only users with ADMIN role can access this endpoint for order monitoring and management purposes. " +
                "The response includes essential order information for the latest orders such as order ID, customer details, " +
                "order status, total amount, and timestamps. This endpoint is particularly useful for real-time order monitoring, " +
                "quick status checks, and providing immediate customer service support for recent orders."
    )
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
    @Operation(summary = "Get all order and user details",
        description = "This endpoint provides administrators with comprehensive information about all orders along with associated user details. " +
                "Only users with ADMIN role can access this endpoint due to the sensitive nature of combined order and customer data. " +
                "The response includes detailed order information merged with customer profiles, providing a complete view of " +
                "order history, customer behavior, and transaction patterns. This endpoint is valuable for customer relationship management, " +
                "business analytics, and comprehensive order management operations."
    )
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
    @Operation(summary = "Change order status",
        description = "This endpoint allows administrators to update the status of existing orders in the system. " +
                "The request requires a valid order ID as a path parameter and the new status as a query parameter. " +
                "Only users with ADMIN role can access this endpoint to ensure proper order management control. " +
                "Common status updates include 'processing', 'shipped', 'delivered', 'cancelled', etc. " +
                "This functionality is crucial for order fulfillment, tracking order progress, and managing the order lifecycle."
    )
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
    @Operation(summary = "Get all detail about order",
        description = "This endpoint allows administrators to retrieve comprehensive and detailed information about a specific order. " +
                "The request requires a valid order ID as a path parameter to identify which order details to retrieve. " +
                "Only users with ADMIN role can access this endpoint for detailed order analysis and management. " +
                "The response includes complete order information such as customer details, itemized product list, " +
                "payment information, shipping details, order history, and all associated metadata. This endpoint is essential " +
                "for in-depth order analysis, customer service, and administrative order management."
    )
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

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(summary = "Get all products in order",
        description = "This endpoint retrieves a list of all products included in a specific order. " +
                "The request requires a valid order ID as a path parameter to identify which order's products to retrieve. " +
                "Both users with USER role (for their own orders) and administrators with ADMIN role (for any order) can access this endpoint. " +
                "The response includes detailed product information for each item in the order such as product names, quantities, " +
                "prices, specifications, and any customizations. This endpoint is useful for order verification, " +
                "inventory management, and customer service purposes."
    )
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

