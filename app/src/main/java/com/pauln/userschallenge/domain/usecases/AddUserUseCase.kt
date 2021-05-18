package com.pauln.userschallenge.domain.usecases

import com.pauln.userschallenge.domain.data.UsersRepository
import com.pauln.userschallenge.util.isEmailInvalid
import io.reactivex.Completable
import javax.inject.Inject

const val INVALID_INPUT = "Please enter a correct name and email"

class AddUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    fun addUser(name: String?, email: String?): Completable {
        return if (areFieldsInvalid(name, email)) {
            Completable.error(Throwable(INVALID_INPUT))
        } else
            usersRepository.addUser(name!!, email!!)
    }

    private fun areFieldsInvalid(userName: String?, userEmail: String?) =
        (isEmailInvalid(userEmail) || userName.isNullOrEmpty())

}
