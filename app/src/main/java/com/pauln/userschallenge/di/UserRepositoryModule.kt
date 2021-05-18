package com.pauln.userschallenge.di

import com.pauln.userschallenge.data.UsersRepositoryImpl
import com.pauln.userschallenge.domain.data.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    abstract fun bindsUsersRepository(userRepositoryImpl: UsersRepositoryImpl) : UsersRepository
}