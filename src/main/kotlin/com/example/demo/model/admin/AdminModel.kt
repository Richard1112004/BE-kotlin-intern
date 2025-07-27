package com.example.demo.model.admin

import jakarta.persistence.*

@Entity
@Table(name = "admins")
open class AdminModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var password: String? = null,
    var email: String? = null,
    var role: String? = null,
)