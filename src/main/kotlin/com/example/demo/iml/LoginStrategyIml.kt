package com.example.demo.provider

import com.example.demo.`interface`.UserLoginStrategy
import com.example.demo.`interface`.LoginStrategy
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class LoginStrategyImpl(
    private val applicationContext: ApplicationContext
) : LoginStrategy {

    override fun getStrategy(userType: String): UserLoginStrategy {
        return applicationContext.getBean(userType, UserLoginStrategy::class.java)
    }
}
