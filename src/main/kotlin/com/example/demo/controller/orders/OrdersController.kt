package com.example.demo.controller.orders

import com.example.demo.dto.request.order.OrderDTO
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
@Tag(name = "orders", description = "API for order operations")
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
    @GetMapping("/all")
    fun getAllOrders(): ResponseEntity<APIRespond<List<OrderModel>>> {
        try {
            // Simulate fetching all orders
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = orderService.getAllOrders(),
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
}