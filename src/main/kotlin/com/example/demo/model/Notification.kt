package com.example.demo.model

import jakarta.persistence.*
import java.time.LocalDate
import java.util.Date


@Entity
@Table(name = "notifications")
data class Notification (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserModel ?= null,

    @Column(nullable = true)
    val title : String ?= null,

    @Column(nullable = true)
    val subTitle : String ?= null,

    @Column(nullable = true)
    val message : String ?= null,

    @Column(nullable = true)
    val createdAt : LocalDate ?= null,

    @Column(nullable = true)
    val read : Boolean ?= null,
)