package com.ubuntuhub.app.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("api/Posts")
    suspend fun getPosts(): List<PostDto>

    @POST("api/Posts")
    suspend fun createPost(
        @Body request: CreatePostRequest
    ): PostDto
}