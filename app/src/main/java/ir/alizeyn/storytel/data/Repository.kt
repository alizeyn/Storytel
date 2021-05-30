package ir.alizeyn.storytel.data.model

import dagger.hilt.android.scopes.ActivityRetainedScoped
import ir.alizeyn.storytel.network.Response
import ir.alizeyn.storytel.network.StorytelServiceApi
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val storytelApi: StorytelServiceApi
) {

    suspend fun getPosts(): Response<List<Post>> {
        return storytelApi.getPosts()
    }

    suspend fun getPhotos(): Response<List<Photo>> {
        return storytelApi.getPhotos()
    }

    suspend fun getComments(postId: Int): Response<List<Comment>> {
        return storytelApi.getComments(postId)
    }
}