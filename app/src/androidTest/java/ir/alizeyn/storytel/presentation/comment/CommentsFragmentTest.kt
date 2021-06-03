package ir.alizeyn.storytel.presentation.comment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class CommentsFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var navController: NavController

    @Before
    fun setup() {
        hiltRule.inject()
    }

//    @Test
//    fun testOnBackPressed() {
//
//        launchFragmentInHiltContainer<CommentsFragment> {
//
//            Navigation.setViewNavController(requireView(), navController)
//            Espresso.pressBack()
//            Mockito.verify(navController).popBackStack()
//        }
//    }
}