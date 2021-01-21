package com.victorizzie.android.apps.jsonplaceholder.data

import androidx.recyclerview.widget.DiffUtil
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
        }
    }
}