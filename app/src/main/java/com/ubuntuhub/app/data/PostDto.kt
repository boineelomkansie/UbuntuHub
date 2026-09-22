package com.ubuntuhub.app.data

data class PostDto(
    val id: Int,
    val userId: Int,
    val title: String,
    val description: String,
    val location: String,
    val category: String,
    val createdAt: String
)