package com.victorizzie.android.apps.jsonplaceholder.data.source.post

import com.victorizzie.android.apps.jsonplaceholder.data.Post
import com.victorizzie.android.apps.jsonplaceholder.status.Result

interface PostsDataSource {
    suspend fun getPosts(start: Int, limit: Int): Result<List<Post>>
    suspend fun getPost(id: Int): Result<Post>
    suspend fun deletePost(id: Int): Result<Any>
    suspend fun patchPost(id: Int, title: String, body: String): Result<Post>
}