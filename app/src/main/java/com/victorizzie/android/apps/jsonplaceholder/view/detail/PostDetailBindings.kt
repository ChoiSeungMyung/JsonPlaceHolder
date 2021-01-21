package com.victorizzie.android.apps.jsonplaceholder.view.detail

import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.victorizzie.android.apps.jsonplaceholder.R
import com.victorizzie.android.apps.jsonplaceholder.data.Comment
import com.victorizzie.android.apps.jsonplaceholder.extension.isDeviceOnline
import com.victorizzie.android.apps.jsonplaceholder.extension.showSnackBar
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.PostDetailViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@BindingAdapter("bind:commentList")
fun setCommentList(listView: RecyclerView, commentList: List<Comment>) {
    (listView.adapter as CommentAdapter).submitList(commentList)
}

@ObsoleteCoroutinesApi
@BindingAdapter("bind:deleteClick", "bind:id")
fun deletePost(view: Button, viewModel: PostDetailViewModel, id: Int) {
    view.setOnClickListener {
        if (view.context.isDeviceOnline()) viewModel.deletePost(id)
        else view.showSnackBar(R.string.toast_check_network)
    }
}