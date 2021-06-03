package ir.alizeyn.storytel.data.repository.post

import com.google.common.truth.Truth.assertThat
import ir.alizeyn.storytel.MainCoroutineRule
import ir.alizeyn.storytel.data.map.PostResponseMapper
import ir.alizeyn.storytel.data.network.FakeStorytelServiceApi
import ir.alizeyn.storytel.data.network.api.StorytelServiceApi
import ir.alizeyn.storytel.data.network.model.Photo
import ir.alizeyn.storytel.data.network.model.Response
import ir.alizeyn.storytel.domain.DataPost
import ir.alizeyn.storytel.domain.StorytelPost
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PostsRepositoryImplTest {

    private lateinit var fakeSuccessfulPostsRepository: PostsRepositoryImpl
    private lateinit var failedPostsRepository: PostsRepositoryImpl

    private lateinit var fakeSuccessfulServiceApi: FakeStorytelServiceApi
    @Mock private lateinit var failedServiceApi: StorytelServiceApi
    @Mock private lateinit var mockPostMapper: PostResponseMapper
    @Mock private lateinit var mockDataPost: DataPost
    @Mock private lateinit var photos: List<Photo>
    @Mock private lateinit var photo: Photo
    @Mock private lateinit var mockStorytelPost: StorytelPost

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {

        MockitoAnnotations.openMocks(this)
        fakeSuccessfulServiceApi = FakeStorytelServiceApi()
        fakeSuccessfulPostsRepository = PostsRepositoryImpl(fakeSuccessfulServiceApi, mockPostMapper)
        failedPostsRepository = PostsRepositoryImpl(failedServiceApi, mockPostMapper)
        `when`(mockPostMapper.map(mockDataPost)).thenReturn(mockStorytelPost)
    }

    @Test
    fun `return successful response`() {

        runBlocking {
            val postsResponse = fakeSuccessfulPostsRepository.getPosts()
            assertThat(postsResponse is Response.Success).isTrue()
        }
    }


    @Test
    fun `return failed response`() {

        runBlocking {
            `when`(failedServiceApi.getPosts()).thenThrow(RuntimeException())
            val postsResponse = failedPostsRepository.getPosts()
            assertThat(postsResponse is Response.Error).isTrue()
        }
    }
}