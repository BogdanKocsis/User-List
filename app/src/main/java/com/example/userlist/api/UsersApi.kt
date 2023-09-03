package com.example.userlist.api

import com.example.userlist.model.dto.UserDto
import retrofit2.http.GET

interface UsersApi {

    @GET()
    suspend fun getUsers(): List<UserDto>
}