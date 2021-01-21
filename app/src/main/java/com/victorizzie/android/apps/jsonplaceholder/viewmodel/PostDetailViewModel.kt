package com.victorizzie.android.apps.jsonplaceholder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.victorizzie.android.apps.jsonplaceholder.R
import com.victorizzie.android.apps.jsonplaceholder.data.Post
import com.victorizzie.android.apps.jsonplaceholder.data.source.PostsRepositoryImpl
import com.victorizzie.android.apps.jsonplaceholder.status.Result
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class PostDetailViewModel(private val postRepository: PostsRepositoryImpl) : BaseViewModel() {
    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post> = _post

    val title = MutableLiveData<String>()
    val body = MutableLiveData<String>()

    fun loadPost(id: Int) {
        setDataLoading(true)
        actor(id) {
            when (val postResult = postRepository.getPost(id)) {
                is Result.Success -> {
                    val post = postResult.data
                    postRepository.refreshPost(post)
                    title.value = post.title
                    body.value = post.body
                    _post.value = post
                }

                is Result.Error -> showSnackBar(R.string.toast_error_get_post)
            }
            setDataLoading(false)
        }
    }

    fun deletePost(id: Int) {
        actor(id) {
            setDataLoading(true)
            val postResult = postRepository.deletePost(id)
            if (postResult is Result.Success) {
                showSnackBar(R.string.toast_success_delete)
            } else {
                showSnackBar(R.string.toast_error_delete)
            }
            setDataLoading(false)
        }
    }

    fun patchPost() {
        val id = post.value?.id ?: throw NullPointerException("Post id is null")
        val title = this.title.value ?: throw NullPointerException("Title is null")
        val body = this.body.value ?: throw NullPointerException("Body is null")

        actor(id) {
            setDataLoading(true)
            when (val postResult = postRepository.patchPost(post.value?.id ?: -1, title, body)) {
                is Result.Success -> {
                    refreshPost(postResult.data)
                    ArrayList<Post>().apply {
                        addAll(postRepository.getCachedPostList())
                    }
                    showSnackBar(R.string.toast_success_modify)
                }

                is Result.Error -> showSnackBar(R.string.toast_error_modify_post)
            }
            setDataLoading(false)
        }
    }

    private fun refreshPost(post: Post) {
        postRepository.refreshPost(post)
    }
}