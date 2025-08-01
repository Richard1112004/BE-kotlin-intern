package com.example.demo.model

import jakarta.persistence.*
import java.time.LocalDate


@Entity
@Table(name = "InstallmentPlan")
data class InstallmentPlan (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    val order: OrderModel ?= null,

    @Column(nullable = true)
    val totalMonth: Int = 0,

    @Column(nullable = true)
    val installmentAmount: Double = 0.0,

    @Column(nullable = true)
    val lateFee: Double = 0.0,

    @Column(nullable = true)
    val status: String = "",

    @Column(nullable = true)
    val startDate: LocalDate = LocalDate.now(),

    @Column(nullable = true)
    val endDate: LocalDate = LocalDate.now() + java.time.Period.ofMonths(totalMonth)
)