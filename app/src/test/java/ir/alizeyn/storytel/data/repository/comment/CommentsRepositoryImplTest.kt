package ir.alizeyn.storytel.data.repository.comment

import com.google.common.truth.Truth.assertThat
import ir.alizeyn.storytel.MainCoroutineRule
import ir.alizeyn.storytel.data.map.CommentResponseMapper
import ir.alizeyn.storytel.data.network.api.StorytelServiceApi
import ir.alizeyn.storytel.data.network.model.Comment
import ir.alizeyn.storytel.data.network.model.Response
import ir.alizeyn.storytel.domain.StorytelComment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

const val POST_ID = 0

class CommentsRepositoryImplTest {

    private lateinit var commentsRepository: CommentsRepositoryImpl

    @Mock private lateinit var serviceApi: StorytelServiceApi
    @Mock private lateinit var mockCommentMapper: CommentResponseMapper
    @Mock private lateinit var mockComment: Comment
    @Mock private lateinit var comments: List<Comment>
    @Mock private lateinit var mockStorytelComment: StorytelComment

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {

        MockitoAnnotations.openMocks(this)
        commentsRepository = CommentsRepositoryImpl(serviceApi, mockCommentMapper)
        `when`(mockCommentMapper.map(mockComment)).thenReturn(mockStorytelComment)
    }

    @Test
    fun `return successful response`() {

        runBlocking {
            `when`(serviceApi.getComments(POST_ID)).thenReturn(comments)
            val postsResponse = commentsRepository.getComments(POST_ID)
            assertThat(postsResponse is Response.Success).isTrue()
        }
    }

    @Test
    fun `return failed response`() {

        runBlocking {
            `when`(serviceApi.getComments(POST_ID)).thenThrow(RuntimeException())
            val postsResponse = commentsRepository.getComments(POST_ID)
            assertThat(postsResponse is Response.Error).isTrue()
        }
    }
}