package com.example.demo.repository.notification

import com.example.demo.model.Notification
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface NotificationRepo : JpaRepository<Notification, Long> {

}