package ir.alizeyn.storytel.data.map.post

import com.google.common.truth.Truth
import ir.alizeyn.storytel.data.map.PostResponseMapper
import ir.alizeyn.storytel.data.network.model.Post
import ir.alizeyn.storytel.domain.DataPost
import org.junit.Before
import org.junit.Test

class PostResponseMapperTest {

    private lateinit var mapper: PostResponseMapper

    @Before
    fun setup() {
        mapper = PostResponseMapper()
    }

    @Test
    fun `Map network comment response to domain level comment model`() {

        val dataPost = DataPost(
            Post(
                0,
                0,
                "title",
                "body",
            ),
            "thumbnailurl",
            "imageUrl"
        )

        val storytelPost = mapper.map(dataPost)
        Truth.assertThat(storytelPost.body).isEqualTo(dataPost.post.body)
        Truth.assertThat(storytelPost.id).isEqualTo(dataPost.post.id)
        Truth.assertThat(storytelPost.title).isEqualTo(dataPost.post.title)
        Truth.assertThat(storytelPost.imageUrl).isEqualTo(dataPost.imageUrl)
        Truth.assertThat(storytelPost.thumbnailUrl).isEqualTo(dataPost.thumbnailUrl)
    }
}