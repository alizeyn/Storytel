package ir.alizeyn.storytel.data.map.comment

import com.google.common.truth.Truth
import ir.alizeyn.storytel.data.map.CommentResponseMapper
import ir.alizeyn.storytel.data.network.model.Comment
import org.junit.Before
import org.junit.Test

class CommentResponseMapperTest {

    private lateinit var mapper: CommentResponseMapper

    @Before
    fun setup() {
        mapper = CommentResponseMapper()
    }

    @Test
    fun `Map network comment response to domain level comment model`() {
        val comment = Comment(
            0,
            0,
            "comment",
            "fake@fakehost.com",
            "body"
        )

        val storytelComment = mapper.map(comment)
        Truth.assertThat(storytelComment.postId).isEqualTo(comment.postId)
        Truth.assertThat(storytelComment.name).isEqualTo(comment.name)
        Truth.assertThat(storytelComment.email).isEqualTo(comment.email)
        Truth.assertThat(storytelComment.body).isEqualTo(comment.body)
    }
}