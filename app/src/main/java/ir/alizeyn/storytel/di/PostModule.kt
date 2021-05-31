package ir.alizeyn.storytel.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.alizeyn.storytel.data.Mapper
import ir.alizeyn.storytel.data.PostRepository
import ir.alizeyn.storytel.data.PostRepositoryImpl
import ir.alizeyn.storytel.data.PostResponseMapper
import ir.alizeyn.storytel.data.model.data.DataPost
import ir.alizeyn.storytel.data.model.domain.StorytelPost
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PostModule {

    @Binds
    abstract fun mapper(mapper: PostResponseMapper): Mapper<DataPost, StorytelPost>

    @Binds
    @Singleton
    abstract fun repository(repository: PostRepositoryImpl): PostRepository
}