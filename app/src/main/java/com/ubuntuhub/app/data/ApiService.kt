package com.ubuntuhub.app.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("api/Posts")
    suspend fun getPosts(): List<PostDto>

    @POST("api/Posts")
    suspend fun createPost(
        @Body request: CreatePostRequest
    ): PostDto

    @POST("api/Users/sync")
    suspend fun syncUser(
        @Body request: UserSyncRequest
    ): UserDto

    @GET("api/Users/by-firebase/{firebaseUid}")
    suspend fun getUserByFirebaseUid(
        @Path("firebaseUid") firebaseUid: String
    ): UserDto
}