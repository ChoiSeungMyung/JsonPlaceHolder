package com.victorizzie.android.apps.jsonplaceholder.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.victorizzie.android.apps.jsonplaceholder.data.source.CommentRepositoryImpl
import com.victorizzie.android.apps.jsonplaceholder.data.source.comment.remote.CommentsRemoteDataSource
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.CommentViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
@Suppress("UNCHECKED_CAST")
class CommentViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)){
            return CommentViewModel(
                CommentRepositoryImpl(
                    commentRemoteDataSource = CommentsRemoteDataSource
                )
            ) as T
        }
        throw IllegalArgumentException("<CommentViewModel> Unknown ViewModel class")
    }
}