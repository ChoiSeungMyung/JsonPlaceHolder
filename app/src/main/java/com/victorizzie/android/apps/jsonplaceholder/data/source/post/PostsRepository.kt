package com.victorizzie.android.apps.jsonplaceholder.data.source.post

import com.victorizzie.android.apps.jsonplaceholder.data.Post
import com.victorizzie.android.apps.jsonplaceholder.status.Result

interface PostsRepository {
    suspend fun getPosts(start: Int = 0, limit: Int = 10, forceUpdate: Boolean): Result<List<Post>>
    suspend fun getPost(id: Int): Result<Post>
    suspend fun deletePost(id: Int): Result<List<Post>>
    suspend fun patchPost(id: Int, title: String, body: String): Result<Post>
    fun cachedPostList(postList: List<Post>)
    fun getCachedPostList(): List<Post>
    fun refreshPost(post: Post)
}