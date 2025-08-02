package com.example.demo.controller.product

import com.example.demo.dto.request.product.ProductDTO
import com.example.demo.dto.respond.APIRespond
import com.example.demo.model.ProductModel
import com.example.demo.service.products.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/api/v1/product")
@RestController
@Tag(name = "Product", description = "API for product operations")
class ProductController (
    private val productService: ProductService
) {
    @Operation(summary = "Get product details")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved product details"),
            ApiResponse(responseCode = "404", description = "Product not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    @GetMapping("/{id}")
    fun getProductDetails(@PathVariable id: Long): ResponseEntity<APIRespond<ProductModel>> {
        try{

            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = productService.getProductById(id),
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
                    data = productService.getAllProducts(),
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

    @Operation(summary = "Create new product")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Successfully created product"),
            ApiResponse(responseCode = "400", description = "Failed to create product"),
        ]
    )
    @PostMapping
    fun createProduct(product: ProductDTO): ResponseEntity<APIRespond<Void>> {
        try {
            productService.createProduct(product)
            return ResponseEntity.status(201).body(
                APIRespond(
                    status = 201,
                    message = "Product created successfully"
                )
            )
        } catch (e: Exception) {
            return ResponseEntity.status(500).body(
                APIRespond(
                    status = 400,
                    message = "Failed to create product: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }


}