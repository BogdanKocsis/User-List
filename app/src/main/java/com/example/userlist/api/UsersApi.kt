package com.example.userlist.api

import com.example.userlist.model.dto.UserDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET("api/")
    suspend fun getUsers(
        @Query("page") pageNumber: Int,
        @Query("results") results: Int = 20,
        @Query("seed") seed: String = "abc"
    ): UserDataWrapper
}