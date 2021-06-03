package ir.alizeyn.storytel.presentation.post

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.alizeyn.storytel.R
import ir.alizeyn.storytel.data.repository.post.FakePostsRepository
import ir.alizeyn.storytel.domain.StorytelPost
import ir.alizeyn.storytel.getOrAwaitValueTest
import ir.alizeyn.storytel.launchFragmentInHiltContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class PostsFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromPostsFragmentToCommentsFragment() {

        val navController = Mockito.mock(NavController::class.java)
        val postViewModel = PostsViewModel(FakePostsRepository())
        val selectedPost = StorytelPost(0, "title", "body", "t", "url")
        launchFragmentInHiltContainer<PostsFragment> {
            Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<PostAdapter.PostViewHolder>(
                    0,
                    click()
                )
            )
            Truth.assertThat(postViewModel.postList.getOrAwaitValueTest())
        }
    }
}