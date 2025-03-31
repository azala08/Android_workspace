package com.example.coroutines

import retrofit2.http.GET

data class UserResponse(val results: List<User>)

interface ApiService {
    @GET("api/")
    suspend fun getUsers(): UserResponse
}