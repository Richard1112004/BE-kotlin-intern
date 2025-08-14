package com.example.demo.controller.notification

import com.example.demo.dto.request.notification.ChangeReadDTO
import com.example.demo.dto.request.notification.NotificationDTO
import com.example.demo.dto.respond.APIRespond
import com.example.demo.model.Notification
import com.example.demo.service.notification.NotificationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/notifications")
@RestController
@Tag(name = "Notifications", description = "The notification service")
class NotificationController (
    private val notificationService: NotificationService,
){

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all notifications",
        description = "This endpoint retrieves all notifications for a specific user identified by their user ID. " +
                "The request requires a valid user ID as a path parameter. Only users with USER role can access this endpoint, " +
                "ensuring notification privacy and security. The response includes all notification details such as " +
                "message content, timestamp, read status, notification type, and related information. " +
                "This endpoint is essential for displaying user notifications, alerts, and system messages in the application."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved all notifications"),
            ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @GetMapping("/{user_id}")
    fun getAllNotifications(@PathVariable user_id: Long): ResponseEntity<APIRespond<List<Notification>>> {
        try {
            return ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = notificationService.getAllNotification(user_id),
                    message = "Successfully retrieved all notifications",
                )
            )
        }
        catch (e: Exception) {
            return ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to retrieve notifications: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Create a notification",
        description = "This endpoint allows authenticated users to create and send new notifications within the system. " +
                "The request body should contain notification details including recipient information, message content, " +
                "notification type, and any relevant metadata. Only users with USER role can access this endpoint. " +
                "Upon successful creation, the notification is stored in the system and delivered to the intended recipient. " +
                "This functionality supports communication features, alerts, and system-generated notifications."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Notification created successfully"),
            ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @PostMapping
    fun createNotification(
        @RequestBody notificationDTO: NotificationDTO
    ): ResponseEntity<APIRespond<Notification>> {
        return try {
            ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = notificationService.addNotification(notificationDTO),
                    message = "Notification created successfully"
                )
            )
        } catch (e: Exception) {
            ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to create notification: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Update a notification",
        description = "This endpoint allows authenticated users to modify existing notifications, particularly to update the read status. " +
                "The request requires a valid notification ID as a path parameter and updated notification data in the request body. " +
                "Only users with USER role can access this endpoint, and typically they can only update their own notifications. " +
                "The most common use case is marking notifications as read or unread, which helps manage notification states " +
                "and provides better user experience for notification management."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Notification updated successfully"),
            ApiResponse(responseCode = "400", description = "Bad request"),
        ]
    )
    @PutMapping("/{id}")
    fun updateNotification(
        @PathVariable id: Long,
        @RequestBody read: ChangeReadDTO,
    ): ResponseEntity<APIRespond<Notification>> {
        return try {
            ResponseEntity.ok(
                APIRespond(
                    status = 200,
                    data = notificationService.updateNotificationReadStatus(id, read.read),
                    message = "Notification updated successfully"
                )
            )
        } catch (e: Exception) {
            ResponseEntity.status(400).body(
                APIRespond(
                    status = 400,
                    message = "Failed to update notification: ${e.message ?: "Unknown error"}"
                )
            )
        }
    }


}