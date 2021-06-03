package ir.alizeyn.storytel.presentation.post

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alizeyn.storytel.data.network.model.Response
import ir.alizeyn.storytel.data.repository.post.PostsRepository
import ir.alizeyn.storytel.domain.StorytelPost
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: PostsRepository
) : ViewModel(), LifecycleObserver {

    private var posts: MutableLiveData<Response<List<StorytelPost>>> = MutableLiveData()
    val postList: LiveData<Response<List<StorytelPost>>>
    get() = posts

    init {
        requestPosts()
    }

    fun requestPosts() = viewModelScope.launch {
        posts.value = repository.getPosts()
    }
}