package com.victorizzie.android.apps.jsonplaceholder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.victorizzie.android.apps.jsonplaceholder.R
import com.victorizzie.android.apps.jsonplaceholder.data.Post
import com.victorizzie.android.apps.jsonplaceholder.data.source.PostsRepositoryImpl
import com.victorizzie.android.apps.jsonplaceholder.status.Result.Error
import com.victorizzie.android.apps.jsonplaceholder.status.Result.Success
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class PostListViewModel(private val postRepository: PostsRepositoryImpl) : BaseViewModel() {
    private val _postList =
        MutableLiveData<List<Post>>().apply { value = postRepository.getCachedPostList() }
    val postList: LiveData<List<Post>> = _postList

    private var start = 0

    fun loadRemotePostList() {
        val cachedList = postRepository.getCachedPostList()
        if (dataLoading.value != true) {
            actor(start) {
                setDataLoading(true)
                when (val postListResult = postRepository.getPosts(it, forceUpdate = true)) {
                    is Success -> {
                        val remotePostList = postListResult.data
                        if (remotePostList.isNotEmpty()) {
                            start += remotePostList.size
                            val postList = ArrayList<Post>().apply {
                                addAll(cachedList)
                                addAll(remotePostList)
                            }

                            _postList.value = postList
                            postRepository.cachedPostList(postList)
                        }
                    }

                    is Error -> showSnackBar(R.string.toast_error_get_list)
                }
                setDataLoading(false)
            }
        }
    }

    fun loadLocalPostList() {
        val cachedList = postRepository.getCachedPostList()
        when (cachedList.isEmpty()) {
            true -> loadRemotePostList()
            false -> {
                val posts = ArrayList<Post>().apply {
                    addAll(cachedList)
                }
                _postList.value = posts
            }
        }
    }
}