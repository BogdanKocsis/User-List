package com.example.userlist.api

import com.example.userlist.model.dto.UserDataWrapper
import retrofit2.http.GET

interface UsersApi {

    @GET("api/?page=0&results=20&seed=abc")
    suspend fun getUsers(): UserDataWrapper
}