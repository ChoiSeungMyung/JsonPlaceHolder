package com.victorizzie.android.apps.jsonplaceholder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.victorizzie.android.apps.jsonplaceholder.R
import com.victorizzie.android.apps.jsonplaceholder.data.Comment
import com.victorizzie.android.apps.jsonplaceholder.data.source.CommentRepositoryImpl
import com.victorizzie.android.apps.jsonplaceholder.status.Result.Success
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ObsoleteCoroutinesApi
class CommentViewModel(
    private val commentsRepository: CommentRepositoryImpl
) : BaseViewModel() {
    private val _commentList = MutableLiveData<List<Comment>>().apply { value = emptyList() }
    val commentList: LiveData<List<Comment>>
        get() = _commentList

    fun loadCommentList(id: Int) {
        setDataLoading(true)

        viewModelScope.launch {
            when(val commentListResult = commentsRepository.getComments(id)) {
                is Success -> {
                    val comments = commentListResult.data
                    _commentList.value = comments
                }

                is Error -> showSnackBar(R.string.toast_error_get_comment)
            }

            setDataLoading(false)
        }
    }
}