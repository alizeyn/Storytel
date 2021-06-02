package ir.alizeyn.storytel.data.repository.comment

import ir.alizeyn.storytel.data.map.Mapper
import ir.alizeyn.storytel.data.network.api.Call
import ir.alizeyn.storytel.data.network.api.StorytelServiceApi
import ir.alizeyn.storytel.data.network.model.Comment
import ir.alizeyn.storytel.domain.StorytelComment
import javax.inject.Inject
import kotlin.math.min

private const val COMMENTS_SIZE_LIMIT = 3

class CommentRepositoryImpl @Inject constructor(
    private val storytelApi: StorytelServiceApi,
    private val commentDataMapper: Mapper<Comment, StorytelComment>
) : CommentRepository {

    override suspend fun getComments(postId: Int) = Call.safeCall {

        val comments = storytelApi.getComments(postId)
        mapComments(
            comments.subList(0, min(COMMENTS_SIZE_LIMIT, comments.size))
        )
    }

    private fun mapComments(comments: List<Comment>): List<StorytelComment> {
        return comments.map {
            commentDataMapper.map(it)
        }
    }
}