package com.victorizzie.android.apps.jsonplaceholder.view.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.victorizzie.android.apps.jsonplaceholder.BuildConfig
import com.victorizzie.android.apps.jsonplaceholder.R
import com.victorizzie.android.apps.jsonplaceholder.databinding.ActPostDetailBinding
import com.victorizzie.android.apps.jsonplaceholder.extension.isDeviceOnline
import com.victorizzie.android.apps.jsonplaceholder.extension.showSnackBar
import com.victorizzie.android.apps.jsonplaceholder.view.modify.PostModifyActivity
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.CommentViewModel
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.PostDetailViewModel
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.factory.CommentViewModelFactory
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.factory.PostDetailViewModelFactory
import kotlinx.coroutines.*

@ObsoleteCoroutinesApi
class PostDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActPostDetailBinding
    private val commentViewModel by lazy {
        ViewModelProvider(this, CommentViewModelFactory()).get(CommentViewModel::class.java)
    }

    private val postDetailViewModel by lazy {
        ViewModelProvider(this, PostDetailViewModelFactory()).get(PostDetailViewModel::class.java)
    }

    private val commentAdapter = CommentAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.act_post_detail)
        setObserver()
        setBinding()
    }

    private fun setBinding() {
        val postId = intent.getIntExtra(BuildConfig.KEY_POST_ID, -1).also {
            if (it != -1 && isDeviceOnline()) {
                postDetailViewModel.loadPost(it)
                commentViewModel.loadCommentList(it)
            } else {
                binding.showSnackBar(R.string.toast_error_get_post)
                finish()
            }
        }

        binding.apply {
            lifecycleOwner = this@PostDetailActivity
            bindCommentViewModel = commentViewModel
            bindPostViewModel = postDetailViewModel
            actPostDetailCommentRecyclerView.adapter = commentAdapter

            actPostDetailModify.setOnClickListener {
                startActivity(Intent(this@PostDetailActivity, PostModifyActivity::class.java).apply {
                    putExtra(BuildConfig.KEY_POST_ID, postId)
                    finish()
                })
            }

            actPostDetailBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun setObserver() {
        commentViewModel.snackBar.observe(this, Observer {
            binding.showSnackBar(it)
        })

        postDetailViewModel.snackBar.observe(this, Observer {
            binding.showSnackBar(it)
            when (it) {
                R.string.toast_success_delete -> {
                    CoroutineScope(Dispatchers.Default).launch {
                        delay(1000)
                        finish()
                    }
                }
            }
        })
    }
}