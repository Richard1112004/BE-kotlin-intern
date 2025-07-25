package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloControl {
    @GetMapping("/hello")
    fun greet(): String {
        return "Hello World"
    }
}