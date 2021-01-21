package com.victorizzie.android.apps.jsonplaceholder.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.victorizzie.android.apps.jsonplaceholder.data.source.PostsRepositoryImpl
import com.victorizzie.android.apps.jsonplaceholder.data.source.post.local.PostsLocalDataSource
import com.victorizzie.android.apps.jsonplaceholder.data.source.post.remote.PostsRemoteDataSource
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.PostDetailViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@Suppress("UNCHECKED_CAST")
@ObsoleteCoroutinesApi
class PostDetailViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostDetailViewModel::class.java)) {
            return PostDetailViewModel(
                PostsRepositoryImpl(
                    postsLocalDataSource = PostsLocalDataSource,
                    postsRemoteDataSource = PostsRemoteDataSource
                )
            ) as T
        }
        throw IllegalArgumentException("<PostDetailViewModel> Unknown ViewModel class")
    }
}