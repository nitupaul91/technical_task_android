package com.pauln.userschallenge.domain.usecases

import com.pauln.userschallenge.domain.data.UsersRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    fun delete(userId: Long): Completable {
        return usersRepository.deleteUser(userId)
    }
}