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
    @Operation(summary = "Get all notifications")
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
    @Operation(summary = "Create a notification")
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
    @Operation(summary = "Update a notification")
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