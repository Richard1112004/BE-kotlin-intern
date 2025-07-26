package com.example.demo.component

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JWTCreateToken(
    @Value("\${jwt.secret_key}") private val secret: String,
    @Value("\${jwt.expirationtime}") private val expiration: Long
) {

    fun createJWT(id: String, email: String, role: String, ttlMillis: Long): String {
        val nowMillis = System.currentTimeMillis()
        val now = Date(nowMillis)
        val signatureAlgorithm = SignatureAlgorithm.HS256

        val apiKeySecretBytes = Base64.getDecoder().decode(secret)
        val signingKey = SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.jcaName)

        val builder = Jwts.builder()
            .setId(id)
            .setIssuedAt(now)
            .setSubject(email)
            .claim("scope", role)
            .signWith(signingKey, signatureAlgorithm)

        if (ttlMillis > 0) {
            val expMillis = nowMillis + ttlMillis
            builder.setExpiration(Date(expMillis))
        }

        return builder.compact()
    }
}
