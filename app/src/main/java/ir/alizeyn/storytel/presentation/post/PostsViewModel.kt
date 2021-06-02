package ir.alizeyn.storytel.presentation.post

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alizeyn.storytel.data.network.model.Response
import ir.alizeyn.storytel.data.repository.post.PostRepository
import ir.alizeyn.storytel.domain.StorytelPost
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel(), LifecycleObserver {

    var posts: MutableLiveData<Response<List<StorytelPost>>> = MutableLiveData()

    init {
        requestPosts()
    }

    fun requestPosts() = viewModelScope.launch {
        posts.value = repository.getPosts()
    }
}