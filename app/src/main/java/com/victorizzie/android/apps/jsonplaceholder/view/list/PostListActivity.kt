package com.victorizzie.android.apps.jsonplaceholder.view.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.victorizzie.android.apps.jsonplaceholder.BuildConfig
import com.victorizzie.android.apps.jsonplaceholder.R
import com.victorizzie.android.apps.jsonplaceholder.databinding.ActPostListBinding
import com.victorizzie.android.apps.jsonplaceholder.extension.isDeviceOnline
import com.victorizzie.android.apps.jsonplaceholder.extension.showSnackBar
import com.victorizzie.android.apps.jsonplaceholder.view.detail.PostDetailActivity
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.PostListViewModel
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.factory.PostListViewModelFactory
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class PostListActivity : AppCompatActivity() {
    private lateinit var binding: ActPostListBinding

    private val postListViewModel by lazy {
        ViewModelProvider(this, PostListViewModelFactory()).get(PostListViewModel::class.java)
    }

    private val postsAdapter =
        PostsAdapter {
            if (isDeviceOnline()) {
                startActivity(Intent(this, PostDetailActivity::class.java).apply {
                    putExtra(BuildConfig.KEY_POST_ID, it.id)
                })
            } else binding.showSnackBar(R.string.toast_check_network)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.act_post_list
        )
        setBinding()
        setObserver()
    }

    override fun onResume() {
        super.onResume()
        loadPost()
    }

    private fun setBinding() {
        binding.apply {
            lifecycleOwner = this@PostListActivity
            bindPostViewModel = postListViewModel
            actListRecyclerView.adapter = postsAdapter
        }
    }

    private fun setObserver() {
        postListViewModel.snackBar.observe(this, Observer {
            binding.showSnackBar(it)
        })
    }

    private fun loadPost() {
        when (isDeviceOnline()) {
            true -> postListViewModel.loadLocalPostList()
            false -> binding.showSnackBar(
                R.string.toast_check_network,
                Snackbar.LENGTH_SHORT
            )
        }
    }
}