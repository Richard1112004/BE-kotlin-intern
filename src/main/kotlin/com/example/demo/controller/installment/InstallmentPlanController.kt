package com.example.demo.controller.installment

import com.example.demo.dto.request.installmentPlan.InstallmentPlanDTO
import com.example.demo.dto.respond.APIRespond
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/api/v1/installment-plan")
@RestController
@Tag(name = "Installment-Plan", description = "API for installment plan operations")
class InstallmentPlanController {
    @Operation(summary = "Install Plan")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successfully installed plan"),
        ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @GetMapping("/{planId}")
    fun installPlan(@PathVariable planId: Int): ResponseEntity<APIRespond<Void>> {
        try {
            // Simulate installing a plan
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
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


    @Operation(summary = "Post installment plans")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successfully posted installment plan"),
        ApiResponse(responseCode = "400", description = "Bad request"),
    ])
    @PostMapping
    fun postInstallmentPlan(@RequestBody req: InstallmentPlanDTO): ResponseEntity<APIRespond<Void>> {
        try {
            // Simulate posting an installment plan
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
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

    @Operation(summary = "Edit installment plans")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Successfully edit installment plans"),
        ApiResponse(responseCode = "400", description = "Bad request"),
    ])
    @PutMapping
    fun editInstallmentPlan(@RequestBody req: InstallmentPlanDTO): ResponseEntity<APIRespond<Void>> {
        try {
            // Simulate editing an installment plan
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

}