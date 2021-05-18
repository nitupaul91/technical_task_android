package com.pauln.userschallenge.domain.data

import com.pauln.userschallenge.domain.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface UsersRepository {

    fun getLastPageUsers(): Single<List<User>>

    fun deleteUser(userId: Long): Completable

    fun addUser(name: String, email: String): Completable

    fun shouldUpdateUserList(): PublishSubject<Boolean>
}
