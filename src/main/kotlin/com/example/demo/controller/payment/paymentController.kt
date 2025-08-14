package com.example.demo.controller.payment

import com.example.demo.dto.request.payment.PaymentDTO
import com.example.demo.dto.respond.APIRespond
import com.example.demo.model.InstallmentPayment
import com.example.demo.service.payment.PaymentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/v1/payment")
@RestController
@Tag(name = "Payment", description = "API for payment operations")
class PaymentController(private val paymentService: PaymentService) {


    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all payments",
        description = "This endpoint retrieves all payment records associated with a specific user. " +
                "The request requires a valid user ID as a path parameter to identify whose payments to retrieve. " +
                "Only users with USER role can access this endpoint, ensuring payment data privacy and security. " +
                "The response includes comprehensive payment information such as payment ID, amount, status, " +
                "payment method, transaction dates, and associated order details. This endpoint is essential for " +
                "payment history, financial tracking, and account management purposes."
    )
    @ApiResponses(value = [ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved all payments"
    ), ApiResponse(
        responseCode = "400",
        description = "Failed to retrieve payments"
    )]
    )
    @GetMapping("/all/{user_id}")
    fun getAllPayments(@PathVariable user_id: Long): ResponseEntity<APIRespond<List<InstallmentPayment>>> {
        try {
            // Simulate fetching all payments
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = paymentService.getAllPayments(user_id),
                    message = "All payments retrieved successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to retrieve payments: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Post payment",
        description = "This endpoint allows authenticated users to submit and process new payment transactions. " +
                "The request body should contain complete payment information including payment amount, method, " +
                "associated order or installment plan details, and any required payment verification data. " +
                "Only users with USER role can access this endpoint. The system validates payment details, " +
                "processes the transaction through appropriate payment gateways, and updates order/installment status accordingly. " +
                "This is a critical endpoint for completing purchases and managing installment payments."
    )
    @ApiResponses(value = [ApiResponse(
        responseCode = "200",
        description = "Successfully posted payment"
    ), ApiResponse(
        responseCode = "400",
        description = "Failed to post payment"
    )]
    )
    @PostMapping
    fun postPayment(@RequestBody req: PaymentDTO): ResponseEntity<APIRespond<InstallmentPayment>> {
        try {
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = paymentService.addPayment(req),
                    message = "Payment posted successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to post payment: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Edit payment",
        description = "This endpoint allows authenticated users to modify existing payment records or update payment information. " +
                "The request requires a valid payment ID as a path parameter and updated payment data in the request body. " +
                "Only users with USER role can access this endpoint, and typically they can only edit their own payments. " +
                "Common use cases include updating payment status, correcting payment details, or modifying payment methods. " +
                "This functionality is important for payment management and handling payment-related issues or corrections."
    )
    @ApiResponses(value = [ApiResponse(
        responseCode = "200",
        description = "Successfully edited payment"
    ), ApiResponse(
        responseCode = "400",
        description = "Failed to edit payment"
    )]
    )
    @PutMapping("/{id}")
    fun editPayment(@RequestBody req: PaymentDTO, @PathVariable id: Long): ResponseEntity<APIRespond<Void>> {
        try {
            paymentService.updatePayment(req, id)
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    message = "Payment edited successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to edit payment: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @Operation(summary = "Get payment",
        description = "This endpoint retrieves payment information associated with a specific installment plan. " +
                "The request requires a valid installment plan ID as a path parameter to identify which payments to retrieve. " +
                "This endpoint provides access to all payment records related to a particular installment plan, " +
                "including payment amounts, due dates, payment status, and transaction history. " +
                "It's commonly used for tracking installment payment progress and managing installment-based purchases."
    )
    @ApiResponses(value = [ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved payments"
    ), ApiResponse(
        responseCode = "400",
        description = "Failed to retrieve payments"
    )]
    )
    @GetMapping("/{installment_plan_id}")
    fun getPayments(@PathVariable installment_plan_id: Long): ResponseEntity<APIRespond<List<InstallmentPayment>>> {
        try {
            // Simulate fetching all payments
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = paymentService.getPayment(installment_plan_id),
                    message = "All payments retrieved successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to retrieve payments: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get payment by product ID",
        description = "This endpoint allows administrators to retrieve all payment records associated with a specific product. " +
                "The request requires a valid product ID as a path parameter. Only users with ADMIN role can access this endpoint " +
                "due to its business intelligence and analytical nature. The response includes comprehensive payment data " +
                "for all transactions involving the specified product, including payment amounts, methods, dates, and customer information. " +
                "This endpoint is valuable for product sales analysis, revenue tracking, and business reporting purposes."
    )
    @ApiResponses(value = [ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved payment by product ID"
    ), ApiResponse(
        responseCode = "400",
        description = "Failed to retrieve payment by product ID"
    )]
    )
    @GetMapping("/product/{product_id}")
    fun getPaymentByProductId(@PathVariable product_id: Long): ResponseEntity<APIRespond<List<InstallmentPayment>>> {
        try {
            // Simulate fetching payments by product ID
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = paymentService.getPaymentByProductId(product_id),
                    message = "Payments retrieved successfully for product ID: $product_id"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to retrieve payments by product ID: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

}