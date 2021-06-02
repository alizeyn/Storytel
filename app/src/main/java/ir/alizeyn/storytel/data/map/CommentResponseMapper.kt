package ir.alizeyn.storytel.data.map

import ir.alizeyn.storytel.domain.StorytelComment
import ir.alizeyn.storytel.data.network.model.Comment
import javax.inject.Inject

class CommentResponseMapper @Inject constructor() : Mapper<Comment, StorytelComment> {

    override fun map(input: Comment): StorytelComment {
        return StorytelComment(input.postId, input.name, input.email, input.body)
    }
}