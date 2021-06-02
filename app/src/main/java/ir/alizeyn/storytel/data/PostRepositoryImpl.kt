package ir.alizeyn.storytel.data

import ir.alizeyn.storytel.data.map.Mapper
import ir.alizeyn.storytel.data.model.data.DataPost
import ir.alizeyn.storytel.data.model.domain.StorytelComment
import ir.alizeyn.storytel.data.model.domain.StorytelPost
import ir.alizeyn.storytel.data.model.network.Comment
import ir.alizeyn.storytel.data.model.network.Photo
import ir.alizeyn.storytel.data.model.network.Post
import ir.alizeyn.storytel.network.Call
import ir.alizeyn.storytel.network.Response
import ir.alizeyn.storytel.network.StorytelServiceApi
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val storytelApi: StorytelServiceApi,
    private val postDataMapper: Mapper<DataPost, StorytelPost>,
    private val commentDataMapper: Mapper<Comment, StorytelComment>
) : PostRepository {

    override suspend fun getPosts(): Response<List<StorytelPost>> {
        return Call.safeCall {
            //todo use flow
            mapPosts(storytelApi.getPosts(), storytelApi.getPhotos())
        }
    }

    override suspend fun getComments(postId: Int, limit: Int): Response<List<StorytelComment>> {
        return Call.safeCall {
            //todo handle less comments than limited
            mapComments(
                storytelApi.getComments(postId)
                    .subList(0, limit)
            )
        }
    }

    private fun mapPosts(posts: List<Post>, photos: List<Photo>): List<StorytelPost> {
        return posts.map {
            val photoItemIndex = photos.indices.random()
            val photo: Photo = photos[photoItemIndex]
            postDataMapper.map(DataPost(it, photo.thumbnailUrl, photo.url))
        }
    }

    private fun mapComments(comments: List<Comment>): List<StorytelComment> {
        return comments.map {
            commentDataMapper.map(it)
        }
    }
}