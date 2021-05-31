package ir.alizeyn.storytel.data.model.data

import ir.alizeyn.storytel.data.model.network.Post

data class DataPost (
    val post: Post,
    val thumbnailUrl: String,
    val imageUrl: String
)