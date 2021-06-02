package ir.alizeyn.storytel.data.repository.post

import ir.alizeyn.storytel.data.map.Mapper
import ir.alizeyn.storytel.data.network.api.Call
import ir.alizeyn.storytel.data.network.api.StorytelServiceApi
import ir.alizeyn.storytel.data.network.model.Photo
import ir.alizeyn.storytel.data.network.model.Post
import ir.alizeyn.storytel.domain.DataPost
import ir.alizeyn.storytel.domain.StorytelPost
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val storytelApi: StorytelServiceApi,
    private val postDataMapper: Mapper<DataPost, StorytelPost>
) : PostRepository {

    override suspend fun getPosts() = Call.safeCall {
        //todo use flow
        mapPosts(storytelApi.getPosts(), storytelApi.getPhotos())
    }

    private fun mapPosts(posts: List<Post>, photos: List<Photo>): List<StorytelPost> {
        return posts.map {
            val photoItemIndex = photos.indices.random()
            val photo: Photo = photos[photoItemIndex]
            postDataMapper.map(DataPost(it, photo.thumbnailUrl, photo.url))
        }
    }
}