package ir.alizeyn.storytel.data.repository

import ir.alizeyn.storytel.domain.StorytelComment
import ir.alizeyn.storytel.domain.StorytelPost
import ir.alizeyn.storytel.data.network.model.Response

interface PostRepository {

    suspend fun getPosts(): Response<List<StorytelPost>>
    suspend fun getComments(postId: Int, limit: Int): Response<List<StorytelComment>>
}