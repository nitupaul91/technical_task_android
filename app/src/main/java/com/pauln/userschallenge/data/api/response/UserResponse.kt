package com.pauln.userschallenge.data.api.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("meta") val meta: UserMeta,
    @SerializedName("data") val data: List<UserData>
)