package ir.alizeyn.storytel.network

import ir.alizeyn.storytel.data.model.Comment
import ir.alizeyn.storytel.data.model.Photo
import ir.alizeyn.storytel.data.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface StorytelServiceApi {

    @GET("/posts")
    suspend fun getPosts(

    ): Response<List<Post>>

    @GET("/photos")
    suspend fun getPhotos(

    ): Response<List<Photo>>

    @GET("/posts/{id}/comments")
    suspend fun getComments(
        @Path("id") id: Int
    ): Response<List<Comment>>
}