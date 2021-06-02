package ir.alizeyn.storytel.presentation.post

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alizeyn.storytel.data.repository.PostRepository
import ir.alizeyn.storytel.domain.StorytelComment
import ir.alizeyn.storytel.domain.StorytelPost
import ir.alizeyn.storytel.data.network.model.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: PostRepository,
    application: Application
) : AndroidViewModel(application), LifecycleObserver {

    var posts: MutableLiveData<Response<List<StorytelPost>>> = MutableLiveData()
    var comments: MutableLiveData<Response<List<StorytelComment>>> = MutableLiveData()


    fun requestPosts() = viewModelScope.launch {
        posts.value = repository.getPosts()
    }

    fun requestComments(postId: Int) = viewModelScope.launch {
        comments.value = repository.getComments(postId, 3)
    }
}