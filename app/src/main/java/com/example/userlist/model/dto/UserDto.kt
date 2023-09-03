package com.example.userlist.model.dto


import com.example.userlist.common.Utils
import com.example.userlist.model.User


data class UserDataWrapper(
    val results: List<UserDto>
)

data class UserDto(
    val name: Name,
    val dob: Dob,
    val nat: String,
    val picture: Picture
)

fun UserDto.toUser() = User(
    name = name.first + " " + name.last,
    age = dob.age,
    country = nat,
    imageUrl = picture.thumbnail,
    hour = Utils.extractHourMinuteFromDate(dob.date)
)


data class Name(
    val first: String,
    val last: String
)

data class Dob(
    val date: String,
    val age: Int
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)