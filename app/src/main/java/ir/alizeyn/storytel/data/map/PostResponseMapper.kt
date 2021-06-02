package ir.alizeyn.storytel.data.map

import ir.alizeyn.storytel.domain.DataPost
import ir.alizeyn.storytel.domain.StorytelPost
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