package com.victorizzie.android.apps.jsonplaceholder.service

import com.victorizzie.android.apps.jsonplaceholder.data.Post
import retrofit2.Response
import retrofit2.http.*

interface PostService {
    @GET("posts")
    suspend fun getPosts(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): Response<List<Post>>

    @GET("posts/{id}")
    suspend fun getPost(
        @Path("id") id: Int
    ): Response<Post>

    @DELETE("posts/{id}")
    suspend fun deletePost(
        @Path("id") id: Int
    ): Response<Any>

    @FormUrlEncoded
    @PATCH("posts/{id}")
    suspend fun patchPost(
        @Path("id") id: Int,
        @Field("title") title: String?,
        @Field("body") body: String?
    ): Response<Post>
}