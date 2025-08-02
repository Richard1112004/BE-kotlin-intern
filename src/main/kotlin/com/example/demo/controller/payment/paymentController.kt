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
    @GetMapping("/all")
    fun getAllPayments(): ResponseEntity<APIRespond<List<InstallmentPayment>>> {
        try {
            // Simulate fetching all payments
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = paymentService.getAllPayments(),
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
    fun postPayment(@RequestBody req: PaymentDTO): ResponseEntity<APIRespond<Void>> {
        try {
            paymentService.addPayment(req)
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
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


}