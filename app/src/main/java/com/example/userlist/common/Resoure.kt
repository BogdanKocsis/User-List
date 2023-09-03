package com.example.userlist.common

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(
        val data: T
    ) : Resource<T>()

    data class Failure(
        val message: String
    ) : Resource<Nothing>()
}