package com.example.demo.service.notification

import com.example.demo.dto.request.notification.NotificationDTO
import com.example.demo.model.Notification
import com.example.demo.repository.cartItem.CartItemRepo
import com.example.demo.repository.notification.NotificationRepo
import com.example.demo.repository.user.UserRepo
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class NotificationService (
    private val notificationRepo: NotificationRepo,
    private val userRepo: UserRepo
) {
    fun getAllNotification(user_id: Long): List<Notification> {
        return notificationRepo.findAllByUserId(user_id);
    }
    fun updateNotificationReadStatus(id: Long, isRead: Boolean): Notification {
        val notification = notificationRepo.findById(id)
            .orElseThrow { IllegalArgumentException("Notification not found with id: $id") }

        val updatedNotification = notification.copy(read = isRead)
        return notificationRepo.save(updatedNotification)
    }
    fun addNotification(notificationDTO: NotificationDTO): Notification {
        val newNotification = Notification(
            user = userRepo.findById(notificationDTO.userId!!)
                .orElseThrow { IllegalArgumentException("User not found with id: ${notificationDTO.userId}") },
            title = notificationDTO.title ?: "",
            subTitle = notificationDTO.subTitle ?: "",
            message = notificationDTO.message ?: "",
            createdAt = notificationDTO.createdAt ?: LocalDate.now(),
            read = notificationDTO.read ?: false
        )

        return notificationRepo.save(newNotification)
    }


}