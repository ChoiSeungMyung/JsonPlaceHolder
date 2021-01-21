package com.victorizzie.android.apps.jsonplaceholder.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.victorizzie.android.apps.jsonplaceholder.R
import com.victorizzie.android.apps.jsonplaceholder.data.Post
import com.victorizzie.android.apps.jsonplaceholder.databinding.ListItemPostBinding

class PostsAdapter(
    private val onClick: (Post) -> Unit
) : ListAdapter<Post, PostsAdapter.PostViewHolder>(Post.DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder.from(
            parent,
            onClick
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postItem = getItem(position)

        holder.binding.run {
            root.tag = postItem
            listItemPostRoot.animation =
                AnimationUtils.loadAnimation(holder.itemView.context, R.anim.list_post_anim)
            post = postItem
        }
    }

    class PostViewHolder(val binding: ListItemPostBinding, onClick: (Post) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.listItemPostRoot.setOnClickListener {
                val post = binding.root.tag as? Post ?: return@setOnClickListener
                onClick(post)
            }
        }

        companion object {
            fun from(parent: ViewGroup, onClick: (Post) -> Unit): PostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPostBinding.inflate(layoutInflater, parent, false)

                return PostViewHolder(
                    binding,
                    onClick
                )
            }
        }
    }

}