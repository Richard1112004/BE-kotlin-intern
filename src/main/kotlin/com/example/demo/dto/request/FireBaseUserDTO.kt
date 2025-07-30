package com.example.demo.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Data Transfer Object for Firebase User")
data class FireBaseUserDTO (
    @Schema(description = "Data Transfer Object for Firebase User")
    val idToken: String? = null,
    val phone: String? = null,
    val password: String? = null,
)