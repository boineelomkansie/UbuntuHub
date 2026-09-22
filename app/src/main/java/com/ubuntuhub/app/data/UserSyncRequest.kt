package com.ubuntuhub.app.data

data class UserSyncRequest(
    val firebaseUid: String,
    val username: String,
    val email: String
)