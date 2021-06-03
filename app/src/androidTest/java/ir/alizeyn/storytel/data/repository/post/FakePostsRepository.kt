package ir.alizeyn.storytel.data.repository.post

import ir.alizeyn.storytel.data.network.model.Response
import ir.alizeyn.storytel.domain.StorytelPost

class FakePostsRepository : PostsRepository {

    override suspend fun getPosts(): Response<List<StorytelPost>> {
        val data = listOf<StorytelPost>(
            StorytelPost(0, "title", "body", "thumbnail", "imageUrl"),
            StorytelPost(1, "title", "body", "thumbnail", "imageUrl"),
            StorytelPost(2, "title", "body", "thumbnail", "imageUrl"),
            StorytelPost(3, "title", "body", "thumbnail", "imageUrl")
        )

        return Response.Success(data)
    }
}