package com.example.demo.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.builders.HttpSecurity

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeHttpRequests {
                authorize("/api/user", permitAll)       // Cho phép truy cập không xác thực
                authorize(anyRequest, authenticated)    // Các đường dẫn khác yêu cầu login
            }
            csrf { disable() }                          // Tắt CSRF
        }
        return http.build() // <- Dòng này fix lỗi bạn gặp
    }
}
