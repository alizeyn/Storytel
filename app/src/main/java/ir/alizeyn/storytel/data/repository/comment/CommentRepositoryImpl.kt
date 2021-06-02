package ir.alizeyn.storytel.data.repository.comment

import ir.alizeyn.storytel.data.map.Mapper
import ir.alizeyn.storytel.data.network.api.Call
import ir.alizeyn.storytel.data.network.api.StorytelServiceApi
import ir.alizeyn.storytel.data.network.model.Comment
import ir.alizeyn.storytel.domain.StorytelComment
import javax.inject.Inject

const val limit = 3

class CommentRepositoryImpl @Inject constructor(
    private val storytelApi: StorytelServiceApi,
    private val commentDataMapper: Mapper<Comment, StorytelComment>
) : CommentRepository {

    override suspend fun getComments(postId: Int) = Call.safeCall {
        //todo handle less comments than limited
        mapComments(
            storytelApi.getComments(postId)
                .subList(0, limit)
        )
    }

    private fun mapComments(comments: List<Comment>): List<StorytelComment> {
        return comments.map {
            commentDataMapper.map(it)
        }
    }
}