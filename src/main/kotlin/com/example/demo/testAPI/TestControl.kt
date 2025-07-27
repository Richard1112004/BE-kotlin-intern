package com.example.demo.testAPI

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestAPI {
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello, user API works!")
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello, admin API works!")
    }
}
