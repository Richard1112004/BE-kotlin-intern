package com.example.demo.service.payment

import com.example.demo.dto.request.payment.PaymentDTO
import com.example.demo.model.InstallmentPayment
import com.example.demo.model.InstallmentPlan
import com.example.demo.repository.installment.InstallmentRepo
import com.example.demo.repository.payment.PaymentRepo
import org.springframework.stereotype.Service


@Service
class PaymentService (
    private val paymentRepo: PaymentRepo,
    private val installmentPlan: InstallmentRepo,
) {
    fun getPayment(id: Long): List<InstallmentPayment> {
        return paymentRepo.findByInstallmentPlan_IdOrderByDueDateAsc(id);
    }

    fun getAllPayments(): List<InstallmentPayment> {
        return paymentRepo.findAll()
    }

    fun getPaymentById(id: Long): InstallmentPayment {
        return paymentRepo.findById(id)
            .orElseThrow { IllegalArgumentException("Payment not found with id: $id") }
    }

    fun addPayment(payment: PaymentDTO): InstallmentPayment {
        val newPayment = InstallmentPayment(
            installmentPlan = installmentPlan.findById(payment.installmentId!!).orElseThrow({IllegalArgumentException("Payment not found with id: ${payment.installmentId}")}),
            amount = payment.amount!!,
            dueDate = payment.due_date!!,
            status = payment.status!!,
        )
        return paymentRepo.save(newPayment)
    }

    fun updatePayment(payment: PaymentDTO, id: Long): InstallmentPayment {
        val existingPayment = paymentRepo.findById(id)
            .orElseThrow { IllegalArgumentException("Payment not found with id: $id") }

        val updatedPayment = existingPayment.copy(
            installmentPlan = payment.installmentId?.let {
                installmentPlan.findById(it).orElse(existingPayment.installmentPlan)
            } ?: existingPayment.installmentPlan,
            amount = payment.amount ?: existingPayment.amount,
            dueDate = payment.due_date ?: existingPayment.dueDate,
            paidDate = payment.paid_date ?: existingPayment.paidDate,
            status = payment.status ?: existingPayment.status
        )

        return paymentRepo.save(updatedPayment)
    }

}