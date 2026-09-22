package com.ubuntuhub.app.data

data class UserDto(
    val id: Int,
    val firebaseUid: String,
    val username: String,
    val email: String,
    val location: String,
    val bio: String,
    val createdAt: String
)