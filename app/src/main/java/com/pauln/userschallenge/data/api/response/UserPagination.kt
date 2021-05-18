package com.pauln.userschallenge.data.api.response

import com.google.gson.annotations.SerializedName

data class UserPagination(
    @SerializedName("total") val total: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("limit") val limit: Int
)