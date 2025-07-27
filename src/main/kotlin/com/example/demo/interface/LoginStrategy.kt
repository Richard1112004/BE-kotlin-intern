package com.example.demo.`interface`

interface LoginStrategy  {
    fun getStrategy(userType: String): UserLoginStrategy
}