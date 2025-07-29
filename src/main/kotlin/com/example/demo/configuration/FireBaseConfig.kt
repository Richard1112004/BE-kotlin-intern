package com.example.demo.configuration

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.FileInputStream

@Configuration
class FireBaseConfig {
    @Bean
    fun initFirebase(): FirebaseApp {
        val resource = ClassPathResource("firebase-service-account.json")
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(resource.inputStream))
            .build()
        return FirebaseApp.initializeApp(options)
    }
}