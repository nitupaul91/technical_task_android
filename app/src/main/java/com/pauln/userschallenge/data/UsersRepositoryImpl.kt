package com.pauln.userschallenge.data

import com.pauln.userschallenge.data.api.UsersApi
import com.pauln.userschallenge.data.api.response.AddUserResponse
import com.pauln.userschallenge.data.api.response.DeleteUserResponse
import com.pauln.userschallenge.domain.User
import com.pauln.userschallenge.domain.data.UsersRepository
import com.pauln.userschallenge.util.relativeTimeToNow
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

const val DELETE_SUCCESS = 204
const val ADD_USER_SUCCESS = 201

@Singleton
class UsersRepositoryImpl @Inject constructor(
    private val usersApi: UsersApi
) : UsersRepository {

    val shouldUpdateListSubject = PublishSubject.create<Boolean>()

    override fun getLastPageUsers(): Single<List<User>> {
        return getPageCount().flatMap { pageCount ->
            usersApi.getUsers(pageCount).map { response ->
                response.data.map { userResponse ->
                    User(
                        userResponse.id,
                        userResponse.name,
                        userResponse.email,
                        userResponse.createdAt.relativeTimeToNow(),
                        userResponse.gender,
                        userResponse.status
                    )
                }
            }
        }
    }

    override fun deleteUser(userId: Long): Completable {
        return usersApi.deleteUser(userId)
            .flatMapCompletable(::handleDeleteUserResponse)
    }

    private fun handleDeleteUserResponse(userDeleteResponse: DeleteUserResponse): Completable {
        return if (userDeleteResponse.code == DELETE_SUCCESS) {
            Completable.complete()
        } else {
            Completable.error(Throwable("error ${userDeleteResponse.code}"))
        }
    }

    override fun addUser(name: String, email: String): Completable {
        val timeStamp = System.currentTimeMillis()
        return usersApi.addUser(
            User(
                timeStamp,
                name,
                email,
                timeStamp.toString(),
                "Male",
                "Active"
            )
        ).flatMapCompletable(::handleAddUserResponse)
    }

    override fun shouldUpdateUserList(): PublishSubject<Boolean> {
        return shouldUpdateListSubject
    }

    private fun handleAddUserResponse(addUserResponse: AddUserResponse): Completable {
        return if (addUserResponse.code == ADD_USER_SUCCESS) {
            shouldUpdateListSubject.onNext(true)
            Completable.complete()
        } else {
            Completable.error(Throwable("error adding user"))
        }
    }

    private fun getPageCount(): Single<Int> {
        return usersApi.getUsers().map {
            it.meta.pagination.pages
        }
    }
}
