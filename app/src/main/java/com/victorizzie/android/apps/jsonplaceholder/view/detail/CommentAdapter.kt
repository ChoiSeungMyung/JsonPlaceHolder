package com.victorizzie.android.apps.jsonplaceholder.view.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.victorizzie.android.apps.jsonplaceholder.data.Comment
import com.victorizzie.android.apps.jsonplaceholder.databinding.ListItemCommentBinding

class CommentAdapter : ListAdapter<Comment, CommentAdapter.CommentViewHolder>(Comment.DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder = CommentViewHolder.from(parent)

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val commentItem = getItem(position)

        holder.binding.comment = commentItem
    }

    class CommentViewHolder(val binding: ListItemCommentBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): CommentViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCommentBinding.inflate(layoutInflater, parent, false)

                return CommentViewHolder(binding)
            }
        }
    }
}