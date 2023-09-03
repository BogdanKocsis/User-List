package com.example.userlist.model.dto

import com.example.userlist.model.User


data class UserDto(
    val name: String,
    val age: Int,
    val country: String,
    val imageUrl: String
)

fun UserDto.toUser() = User(
    name = name,
    age = age,
    country = country,
    imageUrl = imageUrl
)