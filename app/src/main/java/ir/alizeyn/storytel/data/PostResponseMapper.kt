package ir.alizeyn.storytel.data

import ir.alizeyn.storytel.data.model.data.DataPost
import ir.alizeyn.storytel.data.model.domain.StorytelPost
import javax.inject.Inject

class PostResponseMapper @Inject constructor() : Mapper<DataPost, StorytelPost> {

    override fun map(input: DataPost): StorytelPost =
        StorytelPost(
            input.post.id,
            input.post.title,
            input.post.body,
            input.thumbnailUrl,
            input.imageUrl
        )
}