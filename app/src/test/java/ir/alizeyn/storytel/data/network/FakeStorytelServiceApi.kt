package ir.alizeyn.storytel.data.network

import ir.alizeyn.storytel.data.network.api.StorytelServiceApi
import ir.alizeyn.storytel.data.network.model.Comment
import ir.alizeyn.storytel.data.network.model.Photo
import ir.alizeyn.storytel.data.network.model.Post

class FakeStorytelServiceApi : StorytelServiceApi {

    override suspend fun getPosts(): List<Post> {
        return listOf(
            Post(0, 1, "title", "body"),
            Post(0, 2, "title2", "body2")
        )
    }

    override suspend fun getPhotos(): List<Photo> {
        return listOf(
            Photo(0, 1, "title", "url", "thumbUrl"),
            Photo(0, 2, "title2", "url2", "thumbUrl2")
        )
    }

    override suspend fun getComments(id: Int): List<Comment> {
        return listOf()
    }
}