package ir.alizeyn.storytel.domain

import ir.alizeyn.storytel.data.network.model.Post

data class DataPost (
    val post: Post,
    val thumbnailUrl: String,
    val imageUrl: String
)