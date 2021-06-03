package ir.alizeyn.storytel.data.repository.post

import ir.alizeyn.storytel.data.network.model.Response
import ir.alizeyn.storytel.domain.StorytelPost

interface PostsRepository {
    suspend fun getPosts(): Response<List<StorytelPost>>
}