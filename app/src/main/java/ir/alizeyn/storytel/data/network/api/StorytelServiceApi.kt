package ir.alizeyn.storytel.data.network.api

import ir.alizeyn.storytel.data.network.model.Comment
import ir.alizeyn.storytel.data.network.model.Photo
import ir.alizeyn.storytel.data.network.model.Post
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