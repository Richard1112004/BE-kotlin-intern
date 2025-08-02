package com.example.demo.service.installmentPlan

import com.example.demo.dto.request.installmentPlan.InstallmentPlanDTO
import com.example.demo.model.InstallmentPlan
import com.example.demo.repository.cartItem.CartItemRepo
import com.example.demo.repository.installment.InstallmentRepo
import org.springframework.stereotype.Service


@Service
class InstallmentService (
    private val installmentPlanRepo: InstallmentRepo,
    private val cartItemRepo: CartItemRepo,
) {
    fun getAllInstallmentPlans(): List<InstallmentPlan> {
        return installmentPlanRepo.findAll()
    }

    fun getInstallmentPlanById(id: Long): InstallmentPlan {
        return installmentPlanRepo.findById(id)
            .orElseThrow { IllegalArgumentException("Installment plan not found with id: $id") }
    }

    fun addInstallmentPlan(installmentPlan: InstallmentPlanDTO): InstallmentPlan {
        val newInstallmentPlan = InstallmentPlan(
            cartItem = cartItemRepo.findById(installmentPlan.cartItemId).orElseThrow({IllegalArgumentException("Cart item not found with id: ${installmentPlan.cartItemId}")}),
            totalMonth = installmentPlan.totalMonth,
            installmentAmount = installmentPlan.installmentAmount,
            lateFee = installmentPlan.lateFee,
            status = installmentPlan.status,
            startDate = installmentPlan.startDate,
            endDate = installmentPlan.endDate,
            )
        return installmentPlanRepo.save(newInstallmentPlan)
    }

    fun updateInstallmentPlan(installmentPlan: InstallmentPlanDTO, id: Long): InstallmentPlan {
        val existingInstallmentPlan = installmentPlanRepo.findById(id)
            .orElseThrow { IllegalArgumentException("Installment plan not found with id: $id") }

        val updatedInstallmentPlan = existingInstallmentPlan.copy(
            cartItem = installmentPlan.cartItemId?.let {
                cartItemRepo.findById(it).orElse(existingInstallmentPlan.cartItem)
            } ?: existingInstallmentPlan.cartItem,
            totalMonth = installmentPlan.totalMonth ?: existingInstallmentPlan.totalMonth,
            installmentAmount = installmentPlan.installmentAmount ?: existingInstallmentPlan.installmentAmount,
            lateFee = installmentPlan.lateFee ?: existingInstallmentPlan.lateFee,
            status = installmentPlan.status ?: existingInstallmentPlan.status,
            startDate = installmentPlan.startDate ?: existingInstallmentPlan.startDate,
            endDate = installmentPlan.endDate ?: existingInstallmentPlan.endDate
        )

        return installmentPlanRepo.save(updatedInstallmentPlan)
    }


//    fun deleteInstallmentPlan(id: Long) {
//        installmentPlanRepo.deleteById(id)
//    }
}