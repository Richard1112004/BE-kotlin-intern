package com.example.demo.model.user

import jakarta.persistence.*

@Entity
@Table(name = "users")
open class UserModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var password: String? = null,
    var email: String? = null,
    var name: String? = null,
    var phone: String? = null,
    var role: String? = null,
)
