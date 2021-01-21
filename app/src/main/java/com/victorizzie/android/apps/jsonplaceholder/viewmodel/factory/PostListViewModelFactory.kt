package com.victorizzie.android.apps.jsonplaceholder.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.victorizzie.android.apps.jsonplaceholder.data.source.PostsRepositoryImpl
import com.victorizzie.android.apps.jsonplaceholder.data.source.post.local.PostsLocalDataSource
import com.victorizzie.android.apps.jsonplaceholder.data.source.post.remote.PostsRemoteDataSource
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.PostListViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@Suppress("UNCHECKED_CAST")
class PostListViewModelFactory : ViewModelProvider.Factory {
    @ObsoleteCoroutinesApi
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            return PostListViewModel(
                PostsRepositoryImpl(
                    postsRemoteDataSource = PostsRemoteDataSource,
                    postsLocalDataSource = PostsLocalDataSource
                )
            ) as T
        }
        throw IllegalArgumentException("<PostViewModel> Unknown ViewModel class")
    }
}