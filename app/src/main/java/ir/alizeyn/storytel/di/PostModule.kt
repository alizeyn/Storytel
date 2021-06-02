package ir.alizeyn.storytel.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.alizeyn.storytel.data.PostRepository
import ir.alizeyn.storytel.data.PostRepositoryImpl
import ir.alizeyn.storytel.data.map.CommentResponseMapper
import ir.alizeyn.storytel.data.map.Mapper
import ir.alizeyn.storytel.data.map.PostResponseMapper
import ir.alizeyn.storytel.data.model.data.DataPost
import ir.alizeyn.storytel.data.model.domain.StorytelComment
import ir.alizeyn.storytel.data.model.domain.StorytelPost
import ir.alizeyn.storytel.data.model.network.Comment
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