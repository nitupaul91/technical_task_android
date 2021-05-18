package com.pauln.userschallenge.domain

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val createdAt: String?,
    val gender: String,
    val status: String
)
