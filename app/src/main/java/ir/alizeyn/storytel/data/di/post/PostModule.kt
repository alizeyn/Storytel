package ir.alizeyn.storytel.data.di.post

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.alizeyn.storytel.data.map.Mapper
import ir.alizeyn.storytel.data.map.PostResponseMapper
import ir.alizeyn.storytel.data.repository.post.PostRepository
import ir.alizeyn.storytel.data.repository.post.PostRepositoryImpl
import ir.alizeyn.storytel.domain.DataPost
import ir.alizeyn.storytel.domain.StorytelPost
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PostModule {

    @Binds
    abstract fun postMapper(mapper: PostResponseMapper): Mapper<DataPost, StorytelPost>

    @Binds
    @Singleton
    abstract fun repository(repository: PostRepositoryImpl): PostRepository
}