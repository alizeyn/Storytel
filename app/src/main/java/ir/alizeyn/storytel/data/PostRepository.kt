package ir.alizeyn.storytel.data

import ir.alizeyn.storytel.data.model.domain.StorytelPost
import ir.alizeyn.storytel.data.model.network.Comment
import ir.alizeyn.storytel.network.Response

interface PostRepository {

    suspend fun getPosts(): Response<List<StorytelPost>>
    suspend fun getComments(postId: Int): Response<List<Comment>>
}