package ir.alizeyn.storytel.data.repository.comment

import ir.alizeyn.storytel.data.network.model.Response
import ir.alizeyn.storytel.domain.StorytelComment

interface CommentRepository {
    suspend fun getComments(postId: Int): Response<List<StorytelComment>>
}