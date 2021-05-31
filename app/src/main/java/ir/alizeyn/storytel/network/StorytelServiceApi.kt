package ir.alizeyn.storytel.network

import ir.alizeyn.storytel.data.model.network.Comment
import ir.alizeyn.storytel.data.model.network.Photo
import ir.alizeyn.storytel.data.model.network.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface StorytelServiceApi {

    @GET("/posts")
    suspend fun getPosts(
    ): List<Post>

    @GET("/photos")
    suspend fun getPhotos(
    ): List<Photo>

    @GET("/posts/{id}/comments")
    suspend fun getComments(
        @Path("id") id: Int
    ): List<Comment>
}