package com.ubuntuhub.app.data

data class CreatePostRequest(
    val userId: Int,
    val title: String,
    val description: String,
    val location: String,
    val category: String
)