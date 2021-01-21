package com.victorizzie.android.apps.jsonplaceholder.view.modify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.victorizzie.android.apps.jsonplaceholder.BuildConfig
import com.victorizzie.android.apps.jsonplaceholder.R
import com.victorizzie.android.apps.jsonplaceholder.databinding.ActPostModifyBinding
import com.victorizzie.android.apps.jsonplaceholder.extension.hideKeyboard
import com.victorizzie.android.apps.jsonplaceholder.extension.isDeviceOnline
import com.victorizzie.android.apps.jsonplaceholder.extension.showSnackBar
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.PostDetailViewModel
import com.victorizzie.android.apps.jsonplaceholder.viewmodel.factory.PostDetailViewModelFactory
import kotlinx.coroutines.*

@ObsoleteCoroutinesApi
class PostModifyActivity : AppCompatActivity() {
    private lateinit var binding: ActPostModifyBinding
    private val postDetailViewModel by lazy {
        ViewModelProvider(this, PostDetailViewModelFactory()).get(PostDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.act_post_modify)

        intent.getIntExtra(BuildConfig.KEY_POST_ID, -1).also {
            if (it != -1 && isDeviceOnline()) {
                postDetailViewModel.loadPost(it)
            } else {
                binding.showSnackBar(R.string.toast_error_get_post)
            }
        }

        setObserving()
        setBinding()
    }

    private fun setBinding() {
        binding.apply {
            lifecycleOwner = this@PostModifyActivity
            bindPostViewModel = postDetailViewModel
            actPostEditBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun setObserving() {
        postDetailViewModel.snackBar.observe(this, Observer {
            binding.apply {
                hideKeyboard()
                showSnackBar(it)
            }

            when (it) {
                R.string.toast_success_modify -> {
                    CoroutineScope(Dispatchers.Default).launch {
                        delay(1000)
                        finish()
                    }
                }
            }
        })
    }
}