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
                authorize("/api/v1/user/register", permitAll)
                authorize("/swagger-ui/**", permitAll)
                authorize("/v3/api-docs/**", permitAll)
                authorize("/api/v1/auth/user/login", permitAll)
                authorize(anyRequest, authenticated)
            }
            csrf { disable() }                          // Tắt CSRF
        }
        return http.build() // <- Dòng này fix lỗi bạn gặp
    }
}
