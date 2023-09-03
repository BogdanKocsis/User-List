package com.example.userlist.di

import com.example.userlist.api.UsersApi
import com.example.userlist.common.Constants
import com.example.userlist.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesUsersApi(): UsersApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApi::class.java)

    @Provides
    @Singleton
    fun providesUsersRepository(api: UsersApi): UsersRepository {
        return UsersRepository(api)
    }

}