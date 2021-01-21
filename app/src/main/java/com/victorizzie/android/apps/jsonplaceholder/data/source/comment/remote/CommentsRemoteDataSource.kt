package com.victorizzie.android.apps.jsonplaceholder.data.source.comment.remote

import com.victorizzie.android.apps.jsonplaceholder.data.Comment
import com.victorizzie.android.apps.jsonplaceholder.service.CommentService
import com.victorizzie.android.apps.jsonplaceholder.status.Result
import com.victorizzie.android.apps.jsonplaceholder.status.Result.Error
import com.victorizzie.android.apps.jsonplaceholder.status.Result.Success
import com.victorizzie.android.apps.jsonplaceholder.worker.RetrofitWorker

object CommentsRemoteDataSource : CommentRemoteDataSource {
    private val commentService = RetrofitWorker.retrofit.create(CommentService::class.java)
    override suspend fun getComments(id: Int): Result<List<Comment>> = commentService.getComments(id).let {
        val data = it.body()
        if (it.code() == 200 && data != null)
            Success(data)
        else
            Error(Exception("Comment Error"))
    }
}