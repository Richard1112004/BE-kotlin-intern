package com.example.demo.service.products

import com.example.demo.dto.request.product.ProductDTO
import com.example.demo.model.ProductModel
import com.example.demo.repository.product.ProductRepo
import org.springframework.stereotype.Service


@Service
class ProductService (
    private val productRepo: ProductRepo,
) {
    fun getAllProducts(): List<ProductModel> {
        return productRepo.findAll()
    }

    fun getProductById(id: Long): ProductModel {
        return productRepo.findById(id).orElseThrow { IllegalArgumentException("Product not found with id: $id") }
    }

    fun createProduct(product: ProductDTO): ProductModel {
        val newProduct = ProductModel(
            name = product.name,
            description = product.description,
            price = product.price,
            quantity = product.quality,
            image = product.images
        )
        return productRepo.save(newProduct)
    }

//    fun updateProduct(id: Long, product: ProductModel): ProductModel {
//        if (!productRepo.existsById(id)) {
//            throw IllegalArgumentException("Product not found with id: $id")
//        }
//        product.id = id
//        return productRepo.save(product)
//    }

//    fun deleteProduct(id: Long) {
//        if (!productRepo.existsById(id)) {
//            throw IllegalArgumentException("Product not found with id: $id")
//        }
//        productRepo.deleteById(id)
//    }
}