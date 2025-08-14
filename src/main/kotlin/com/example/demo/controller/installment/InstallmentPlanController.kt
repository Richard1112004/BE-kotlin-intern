package com.example.demo.controller.installment

import com.example.demo.dto.request.installmentPlan.InstallmentPlanDTO
import com.example.demo.dto.respond.APIRespond
import com.example.demo.model.InstallmentPlan
import com.example.demo.service.installmentPlan.InstallmentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/v1/installment-plan")
@RestController
@Tag(name = "Installment-Plan", description = "API for installment plan operations")
class InstallmentPlanController (
     private val installmentService: InstallmentService
) {
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Install Plan",
        description = "This endpoint allows authenticated users to retrieve details of a specific installment plan by its ID. " +
                "The request requires a valid plan ID as a path parameter. Only users with USER role can access this endpoint. " +
                "The response includes comprehensive installment plan information such as payment schedule, interest rates, " +
                "duration, monthly payment amounts, and terms and conditions. This endpoint is typically used when users " +
                "want to view installment plan details before making a purchase decision or during the checkout process."
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successfully installed plan"),
        ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @GetMapping("/{planId}")
    fun installPlan(@PathVariable planId: Int): ResponseEntity<APIRespond<InstallmentPlan>> {
        try {
            // Simulate installing a plan
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = installmentService.getInstallmentPlanById(planId.toLong()),
                    message = "Installment plan installed successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to install plan: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Post installment plans",
        description = "This endpoint allows authenticated users to create and submit new installment plans. " +
                "The request body should contain complete installment plan information including payment terms, " +
                "duration, interest rates, payment schedule, and associated product or order details. " +
                "Only users with USER role can access this endpoint. Upon successful creation, the installment plan " +
                "is registered in the system and becomes available for payment processing. This endpoint is essential " +
                "for setting up flexible payment options and managing installment-based purchases."
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successfully posted installment plan"),
        ApiResponse(responseCode = "400", description = "Bad request"),
    ])
    @PostMapping
    fun postInstallmentPlan(@RequestBody req: InstallmentPlanDTO): ResponseEntity<APIRespond<InstallmentPlan>> {
        try {

            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = installmentService.addInstallmentPlan(req),
                    message = "Installment plan posted successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to post installment plan: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Edit installment plans",
        description = "This endpoint allows authenticated users to modify existing installment plan details. " +
                "The request requires a valid installment plan ID as a path parameter and updated plan information in the request body. " +
                "Only users with USER role can access this endpoint, and typically they can only edit their own installment plans. " +
                "Users can update payment schedules, modify terms, or adjust plan details as needed. " +
                "This functionality is important for managing installment agreements and accommodating changes in payment preferences."
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successfully edit installment plans"),
        ApiResponse(responseCode = "400", description = "Bad request"),
    ])
    @PutMapping("/{id}")
    fun editInstallmentPlan(@RequestBody req: InstallmentPlanDTO, @PathVariable id: Long): ResponseEntity<APIRespond<Void>> {
        try {
            installmentService.updateInstallmentPlan(req, id)
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    message = "Installment plan edited successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to edit installment plan: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }


    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all install Plan",
        description = "This endpoint retrieves all installment plans associated with a specific user. " +
                "The request requires a valid user ID as a path parameter to identify whose installment plans to retrieve. " +
                "Only users with USER role can access this endpoint, ensuring installment plan privacy and security. " +
                "The response includes comprehensive information about all user's installment plans including payment status, " +
                "remaining balances, payment schedules, and plan details. This endpoint is essential for users to manage " +
                "their installment commitments and track payment progress across multiple plans."
    )
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successfully all installed plan"),
        ApiResponse(responseCode = "400", description = "Bad request"),
    ]
    )
    @GetMapping("/all/{user_id}")
    fun getAllInstallPlan(@PathVariable user_id: Long): ResponseEntity<APIRespond<List<InstallmentPlan>>> {
        try {
            // Simulate installing a plan
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = installmentService.getAllInstallmentPlans(user_id),
                    message = "Installment plan installed successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to install plan: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }
}