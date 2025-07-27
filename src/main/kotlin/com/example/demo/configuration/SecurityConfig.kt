package com.example.demo.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.config.annotation.web.invoke // Ensure this import is present

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtDecoder: JwtDecoder,
        jwtConverterConfig: JWTConverterConfig
    ): SecurityFilterChain {

        http
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers("/api/v1/user/register", "/api/v1/admin/register", "/api/v1/auth/user/login", "/api/v1/auth/admin/login", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .anyRequest().authenticated()
            }

        http.csrf { csrf -> csrf.disable() }

        http.oauth2ResourceServer { oauth2 ->
            oauth2.jwt { jwt ->
                jwt.decoder(jwtDecoder)
                jwt.jwtAuthenticationConverter(jwtConverterConfig.jwtAuthenticationConverter())
            }
        }

        return http.build()
    }

}
