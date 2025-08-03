package com.example.demo.model

import jakarta.persistence.*


@Entity
@Table(name = "payments")
data class InstallmentPayment (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "installment_plan_id", nullable = false)
    val installmentPlan: InstallmentPlan? = null,

    @Column(nullable = true)
    val amount: Double = 0.0,

    @Column(nullable = true)
    val dueDate: java.time.LocalDate = java.time.LocalDate.now(),

    @Column(nullable = true)
    val paidDate : java.time.LocalDate? = null,

    @Column(nullable = true)
    val status: String = "PENDING"
)