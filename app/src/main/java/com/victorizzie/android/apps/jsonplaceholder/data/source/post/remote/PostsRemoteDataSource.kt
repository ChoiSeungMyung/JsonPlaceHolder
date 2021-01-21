package com.victorizzie.android.apps.jsonplaceholder.data.source.post.remote

import com.victorizzie.android.apps.jsonplaceholder.data.Post
import com.victorizzie.android.apps.jsonplaceholder.data.source.post.PostsDataSource
import com.victorizzie.android.apps.jsonplaceholder.status.Result
import com.victorizzie.android.apps.jsonplaceholder.status.Result.Success
import com.victorizzie.android.apps.jsonplaceholder.status.Result.Error
import com.victorizzie.android.apps.jsonplaceholder.service.PostService
import com.victorizzie.android.apps.jsonplaceholder.worker.RetrofitWorker

object PostsRemoteDataSource :
    PostsDataSource {
    private val postService = RetrofitWorker.retrofit.create(PostService::class.java)

    override suspend fun getPosts(start: Int, limit: Int): Result<List<Post>> =
        postService.getPosts(start, limit).let {
            val data = it.body()
            if (it.code() == 200 && data != null)
                Success(data)
            else
                Error(Exception("Post list is Null"))
        }

    override suspend fun getPost(id: Int): Result<Post> = postService.getPost(id).let {
        val data = it.body()
        if (it.code() == 200 && data != null)
            Success(data)
        else
            Error(Exception("Not found post"))
    }

    override suspend fun deletePost(id: Int): Result<List<Post>> =
        postService.deletePost(id).let {
            if (it.code() == 200)
                Success(emptyList())
            else
                Error(Exception("Fail delete post"))
        }

    override suspend fun patchPost(id: Int, title: String, body: String): Result<Post> =
        postService.patchPost(id, title, body).let {
            val data = it.body()
            if (it.code() == 200 && data != null)
                Success(data)
            else
                Error(Exception("Fail patch post"))
        }
}