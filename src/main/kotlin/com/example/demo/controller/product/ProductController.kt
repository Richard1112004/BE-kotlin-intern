package com.example.demo.controller.product

import com.example.demo.dto.respond.APIRespond
import com.example.demo.model.ProductModel
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/api/v1/product")
@RestController
@Tag(name = "Product", description = "API for product operations")
class ProductController {
    @Operation(summary = "Get product details")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved product details"),
            ApiResponse(responseCode = "404", description = "Product not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    @GetMapping("/{id}")
    fun getProductDetails(@PathVariable id: Int): ResponseEntity<APIRespond<ProductModel>> {
        try{
            // Simulate fetching product details
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = ProductModel(1, "Sample Product", "This is a sample product description", 99.99),
                    message = "Product details retrieved successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(500).body(
                APIRespond(
                    status = 400,
                    message = "Failed to retrieve product details: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }


    @Operation(summary = "Get all products")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved all products"),
            ApiResponse(responseCode = "400", description = "Failed to retrieve products"),
        ]
    )
    @GetMapping("/all")
    fun getAllProducts(): ResponseEntity<APIRespond<List<ProductModel>>> {
        try {
            // Simulate fetching all products
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = listOf(
                        ProductModel(1, "Product 1", "Description for product 1", 29.99),
                        ProductModel(2, "Product 2", "Description for product 2", 49.99),
                        ProductModel(3, "Product 3", "Description for product 3", 19.99)
                    ),
                    message = "All products retrieved successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(500).body(
                APIRespond(
                    status = 400,
                    message = "Failed to retrieve products: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }


}