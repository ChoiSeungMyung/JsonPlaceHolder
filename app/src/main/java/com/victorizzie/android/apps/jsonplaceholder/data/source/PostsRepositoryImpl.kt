package com.victorizzie.android.apps.jsonplaceholder.data.source

import com.victorizzie.android.apps.jsonplaceholder.data.Post
import com.victorizzie.android.apps.jsonplaceholder.data.source.post.PostsDataSource
import com.victorizzie.android.apps.jsonplaceholder.data.source.post.PostsRepository
import com.victorizzie.android.apps.jsonplaceholder.data.source.post.local.PostsLocalDataSource
import com.victorizzie.android.apps.jsonplaceholder.status.Result

class PostsRepositoryImpl (
    private val postsRemoteDataSource: PostsDataSource,
    private val postsLocalDataSource: PostsLocalDataSource
) : PostsRepository {
    override suspend fun getPosts(
        start: Int,
        limit: Int,
        forceUpdate: Boolean
    ): Result<List<Post>> = if (forceUpdate) postsRemoteDataSource.getPosts(start, limit)
    else postsLocalDataSource.getPosts(start, limit)

    override suspend fun getPost(id: Int): Result<Post> = postsRemoteDataSource.getPost(id)

    override suspend fun deletePost(id: Int): Result<List<Post>> {
        val delete = postsRemoteDataSource.deletePost(id)
        return if (delete is Result.Success) {
            postsLocalDataSource.deletePost(id)
        } else {
            Result.Error(Exception("Illegal State"))
        }
    }

    override suspend fun patchPost(id: Int, title: String, body: String): Result<Post> {
        return postsRemoteDataSource.patchPost(id, title, body)
    }

    override fun cachedPostList(postList: List<Post>) =
        postsLocalDataSource.cachingPostList(postList)

    override fun getCachedPostList(): List<Post> = postsLocalDataSource.getCachedPostList()

    override fun refreshPost(post: Post) = postsLocalDataSource.patchPost(post)
}