package com.pauln.userschallenge.domain.usecases

import com.pauln.userschallenge.domain.User
import com.pauln.userschallenge.domain.data.UsersRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetLastPageUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    fun get(): Single<List<User>> {
        return usersRepository.getLastPageUsers()
    }

    fun shouldUpdateUserList(): Observable<Boolean> {
        return usersRepository.shouldUpdateUserList()
    }
}
