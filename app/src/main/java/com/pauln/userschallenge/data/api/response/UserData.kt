package com.pauln.userschallenge.data.api.response

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("status") val status: String
)
