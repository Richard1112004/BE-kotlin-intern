package com.example.demo.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import java.util.*
import javax.crypto.spec.SecretKeySpec


@Configuration
class JWTDecoderConfig (
    @Value("\${jwt.secret_key}") private val secretKey: String,
) {

    @Bean
    fun jwtDecoder(): JwtDecoder {
        val decodedSecret = Base64.getDecoder().decode(secretKey)
        val secretKeySpec = SecretKeySpec(decodedSecret, "HmacSHA256")

        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
            .macAlgorithm(MacAlgorithm.HS256)
            .build()
    }

}