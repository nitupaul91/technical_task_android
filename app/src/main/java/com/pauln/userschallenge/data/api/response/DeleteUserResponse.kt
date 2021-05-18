package com.pauln.userschallenge.data.api.response

import com.google.gson.annotations.SerializedName

data class DeleteUserResponse(
    @SerializedName("code") val code: Int
)