package com.victorizzie.android.apps.jsonplaceholder.service

import com.victorizzie.android.apps.jsonplaceholder.data.Comment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentService {
    @GET("posts/{id}/comments")
    suspend fun getComments(
        @Path("id") id: Int
    ): Response<List<Comment>>
}