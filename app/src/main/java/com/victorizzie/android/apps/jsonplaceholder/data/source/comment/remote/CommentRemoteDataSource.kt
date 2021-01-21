package com.victorizzie.android.apps.jsonplaceholder.data.source.comment.remote

import com.victorizzie.android.apps.jsonplaceholder.data.Comment
import com.victorizzie.android.apps.jsonplaceholder.status.Result

interface CommentRemoteDataSource {
    suspend fun getComments(id: Int): Result<List<Comment>>
}