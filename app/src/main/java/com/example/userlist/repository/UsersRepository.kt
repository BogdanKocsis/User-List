package com.example.userlist.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.userlist.api.UsersApi
import com.example.userlist.common.Resource
import com.example.userlist.model.User
import com.example.userlist.model.dto.toUser
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UsersRepository @Inject constructor(private val api: UsersApi) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getUsers(): Resource<List<User>> =
        try {
            val users = api.getUsers().results.map { userDto ->
                userDto.toUser()
            }
            Resource.Success(users)
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Failure("Couldn't reach server. Check your internet connection.")
        } catch (e: HttpException) {
            Resource.Failure("An unexpected error occurred.")
        }
}