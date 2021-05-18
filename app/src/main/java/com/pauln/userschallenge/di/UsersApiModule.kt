package com.pauln.userschallenge.di

import com.pauln.userschallenge.data.api.RetrofitFactory
import com.pauln.userschallenge.data.api.UsersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UsersApiModule {

    @Provides
    fun providesUsersApi(): UsersApi {
        return RetrofitFactory.getRetrofitInstance().create(
            UsersApi::class.java
        )
    }
}