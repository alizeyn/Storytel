package ir.alizeyn.storytel.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.alizeyn.storytel.data.repository.PostRepository
import ir.alizeyn.storytel.data.repository.PostRepositoryImpl
import ir.alizeyn.storytel.data.map.CommentResponseMapper
import ir.alizeyn.storytel.data.map.Mapper
import ir.alizeyn.storytel.data.map.PostResponseMapper
import ir.alizeyn.storytel.domain.DataPost
import ir.alizeyn.storytel.data.network.model.Comment
import ir.alizeyn.storytel.domain.StorytelComment
import ir.alizeyn.storytel.domain.StorytelPost
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PostModule {

    @Binds
    abstract fun postMapper(mapper: PostResponseMapper): Mapper<DataPost, StorytelPost>

    @Binds
    abstract fun commentMapper(mapper: CommentResponseMapper): Mapper<Comment, StorytelComment>

    @Binds
    @Singleton
    abstract fun repository(repository: PostRepositoryImpl): PostRepository
}