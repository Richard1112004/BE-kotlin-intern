package com.example.demo.repository.admin

import com.example.demo.model.admin.AdminModel
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepo : JpaRepository<AdminModel, Long> {
    fun findByEmail(email: String): AdminModel?
}