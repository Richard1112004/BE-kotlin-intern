package com.example.demo.model

import jakarta.persistence.*


@Entity
@Table(name = "products")
data class ProductModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var description: String = "",

    @Column(nullable = false)
    var price: Double = 0.0,

    @Column(nullable = false)
    var quantity: Long = 0,

    @Column(nullable = false)
    var image: String = "",

)