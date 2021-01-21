package com.victorizzie.android.apps.jsonplaceholder.view.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.victorizzie.android.apps.jsonplaceholder.R
import com.victorizzie.android.apps.jsonplaceholder.data.Post
import com.victorizzie.android.apps.jsonplaceholder.extension.isDeviceOnline
import com.victorizzie.android.apps.jsonplaceholder.extension.showSnackBar
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.PostListViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
@BindingAdapter("bind:scrollListener")
fun loadMore(recyclerView: RecyclerView, viewModel: PostListViewModel) {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                if (recyclerView.context.isDeviceOnline()) viewModel.loadRemotePostList()
                else recyclerView.showSnackBar(R.string.toast_check_network)
            }
        }
    })
}

@BindingAdapter("bind:postList")
fun setItems(listView: RecyclerView, items: List<Post>?) {
    items?.let {
        (listView.adapter as PostsAdapter).submitList(it)
    }
}