package com.victorizzie.android.apps.jsonplaceholder.data.source

import com.victorizzie.android.apps.jsonplaceholder.data.Comment
import com.victorizzie.android.apps.jsonplaceholder.data.source.comment.CommentRepository
import com.victorizzie.android.apps.jsonplaceholder.data.source.comment.remote.CommentsRemoteDataSource
import com.victorizzie.android.apps.jsonplaceholder.status.Result

class CommentRepositoryImpl (
    private val commentRemoteDataSource: CommentsRemoteDataSource
) : CommentRepository {
    override suspend fun getComments(id: Int): Result<List<Comment>> = commentRemoteDataSource.getComments(id)
}