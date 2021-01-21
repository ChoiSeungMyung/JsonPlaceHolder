package com.victorizzie.android.apps.jsonplaceholder.data.source.post.local

import com.victorizzie.android.apps.jsonplaceholder.data.Post
import com.victorizzie.android.apps.jsonplaceholder.data.source.post.PostsDataSource
import com.victorizzie.android.apps.jsonplaceholder.status.Result

object PostsLocalDataSource : PostsDataSource {
    private var cachedPosts: List<Post> = emptyList()

    fun cachingPostList(postList: List<Post>) {
        cachedPosts = postList
    }

    fun getCachedPostList(): List<Post> = cachedPosts

    fun patchPost(post: Post) {
        cachedPosts = cachedPosts.map {
            if (it.id == post.id) post
            else it
        }
    }

    override suspend fun getPosts(start: Int, limit: Int) = Result.Success(cachedPosts)

    override suspend fun deletePost(id: Int): Result<List<Post>> {
        cachedPosts = cachedPosts.filter { it.id != id }
        return Result.Success(cachedPosts)
    }

    override suspend fun getPost(id: Int): Result<Post> =
        Result.Success(cachedPosts.first { it.id == id })

    override suspend fun patchPost(id: Int, title: String, body: String): Result<Post> {
        cachedPosts = cachedPosts.map {
            if (it.id == id) it.copy(
                userId = it.id,
                id = it.userId,
                title = title,
                body = body
            )
            else it
        }
        return Result.Success(cachedPosts.first { it.id == id })
    }
}