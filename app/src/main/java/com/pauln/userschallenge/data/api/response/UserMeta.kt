package com.pauln.userschallenge.data.api.response

import com.google.gson.annotations.SerializedName

data class UserMeta(
    @SerializedName("pagination") val pagination: UserPagination
)