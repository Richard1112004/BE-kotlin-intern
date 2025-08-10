package com.example.demo.repository.user


import com.example.demo.dto.request.user.UserDTO
import com.example.demo.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepo : JpaRepository<UserModel, Long> {
    fun findByEmail(username: String): UserModel?

    @Query("""
        SELECT new com.example.demo.dto.request.user.UserDTO(
            u.id,
            u.name,
            u.email,
            u.phone
        )
        FROM UserModel u
        WHERE u.role = 'USER'
    """)
    fun findAllUserDTO(): List<UserDTO>
}