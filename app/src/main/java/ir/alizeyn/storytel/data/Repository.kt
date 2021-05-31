package ir.alizeyn.storytel.data.model

import dagger.hilt.android.scopes.ActivityRetainedScoped
import ir.alizeyn.storytel.data.Mapper
import ir.alizeyn.storytel.data.model.data.DataPost
import ir.alizeyn.storytel.data.model.domain.StorytelPost
import ir.alizeyn.storytel.data.model.network.Comment
import ir.alizeyn.storytel.data.model.network.Photo
import ir.alizeyn.storytel.data.model.network.Post
import ir.alizeyn.storytel.network.Call
import ir.alizeyn.storytel.network.Response
import ir.alizeyn.storytel.network.StorytelServiceApi
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val storytelApi: StorytelServiceApi,
    private val postDataMapper: Mapper<DataPost, StorytelPost>
) {

    suspend fun getPosts(): Response<List<StorytelPost>> {
        return Call.safeCall {
            mapPosts(storytelApi.getPosts(), storytelApi.getPhotos())
        }
    }

    suspend fun getComments(postId: Int): Response<List<Comment>> {
        return Call.safeCall {
            storytelApi.getComments(postId)
        }
    }

    private fun mapPosts(posts: List<Post>, photos: List<Photo>): List<StorytelPost> {
        return posts.map {
            val photoItemIndex = photos.indices.random()
            val thumbnailUrl = photos[photoItemIndex].thumbnailUrl
            postDataMapper.map(DataPost(it, thumbnailUrl))
        }
    }
}