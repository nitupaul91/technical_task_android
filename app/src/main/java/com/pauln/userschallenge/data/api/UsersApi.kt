package com.pauln.userschallenge.data.api

import com.pauln.userschallenge.data.api.response.AddUserResponse
import com.pauln.userschallenge.data.api.response.DeleteUserResponse
import com.pauln.userschallenge.data.api.response.UserResponse
import com.pauln.userschallenge.domain.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://gorest.co.in/public-api/"
const val API_KEY = "Bearer e97bf9530e11083a2d173039fae838448214b382727ee651bf8b4122a0d2d4f1"

interface UsersApi {

    @GET("users")
    fun getUsers(@Query(value = "page") pageNumber: Int? = null): Single<UserResponse>

    @Headers("Authorization: $API_KEY")
    @DELETE("users/{id}")
    fun deleteUser(@Path("id") userId: Long): Single<DeleteUserResponse>

    @Headers("Authorization: $API_KEY")
    @POST("users")
    fun addUser(@Body user: User): Single<AddUserResponse>

}