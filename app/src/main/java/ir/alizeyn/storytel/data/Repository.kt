package ir.alizeyn.storytel.data.model

import dagger.hilt.android.scopes.ActivityRetainedScoped
import ir.alizeyn.storytel.network.Call
import ir.alizeyn.storytel.network.Response
import ir.alizeyn.storytel.network.StorytelServiceApi
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val storytelApi: StorytelServiceApi
) {

    suspend fun getPosts(): Response<List<Post>> {
        return Call.safeCall {
            storytelApi.getPosts()
        }
    }

    suspend fun getPhotos(): Response<List<Photo>> {
        return Call.safeCall {
            storytelApi.getPhotos()
        }
    }

    suspend fun getComments(postId: Int): Response<List<Comment>> {
        return Call.safeCall {
            storytelApi.getComments(postId)
        }
    }
}