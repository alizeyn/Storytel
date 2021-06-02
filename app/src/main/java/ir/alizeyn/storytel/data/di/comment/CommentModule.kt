package ir.alizeyn.storytel.data.di.comment

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.alizeyn.storytel.data.map.CommentResponseMapper
import ir.alizeyn.storytel.data.map.Mapper
import ir.alizeyn.storytel.data.network.model.Comment
import ir.alizeyn.storytel.data.repository.comment.CommentRepository
import ir.alizeyn.storytel.data.repository.comment.CommentRepositoryImpl
import ir.alizeyn.storytel.domain.StorytelComment
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CommentModule {

    @Binds
    abstract fun commentMapper(mapper: CommentResponseMapper): Mapper<Comment, StorytelComment>

    @Binds
    @Singleton
    abstract fun repository(repository: CommentRepositoryImpl): CommentRepository
}