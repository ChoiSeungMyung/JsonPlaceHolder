package com.victorizzie.android.apps.jsonplaceholder.data

import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
) {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Comment>() {
            override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean = oldItem == newItem
        }
    }
}