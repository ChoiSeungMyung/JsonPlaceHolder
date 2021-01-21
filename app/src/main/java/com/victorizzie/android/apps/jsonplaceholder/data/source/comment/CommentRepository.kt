package com.victorizzie.android.apps.jsonplaceholder.data.source.comment

import com.victorizzie.android.apps.jsonplaceholder.data.Comment
import com.victorizzie.android.apps.jsonplaceholder.status.Result

interface CommentRepository {
    suspend fun getComments(id: Int): Result<List<Comment>>
}