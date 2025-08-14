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
    @Operation(summary = "Get all payments")
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
    @Operation(summary = "Post payment")
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
    @Operation(summary = "Edit payment")
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

    @Operation(summary = "Get payment")
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
    @Operation(summary = "Get payment by product ID")
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