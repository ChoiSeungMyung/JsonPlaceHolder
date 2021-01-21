package com.victorizzie.android.apps.jsonplaceholder.view.modify

import android.widget.Button
import androidx.databinding.BindingAdapter
import com.victorizzie.android.apps.jsonplaceholder.extension.isDeviceOnline
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.PostDetailViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
@BindingAdapter("bind:modifyPost")
fun modifyPost(view: Button, viewModel: PostDetailViewModel){
    view.setOnClickListener {
        val context = view.context
        if (context.isDeviceOnline()) {
            viewModel.patchPost()
        }
    }
}