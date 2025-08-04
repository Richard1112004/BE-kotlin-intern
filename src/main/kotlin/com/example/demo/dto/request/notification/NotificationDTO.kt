package com.example.demo.dto.request.notification

import java.time.LocalDate

data class NotificationDTO (
    val userId : Long ?= null,
    val title : String ?= null,
    val subTitle : String ?= null,
    val message : String ?= null,
    val createdAt : LocalDate?= null,
    val read : Boolean ?= false
)