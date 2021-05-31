package ir.alizeyn.storytel.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alizeyn.storytel.data.PostRepository
import ir.alizeyn.storytel.data.model.domain.StorytelPost
import ir.alizeyn.storytel.data.model.network.Comment
import ir.alizeyn.storytel.network.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: PostRepository,
    application: Application
) : AndroidViewModel(application), LifecycleObserver {

    var posts: MutableLiveData<Response<List<StorytelPost>>> = MutableLiveData()
    var comments: MutableLiveData<Response<List<Comment>>> = MutableLiveData()


    fun requestPosts() = viewModelScope.launch {
        posts.value = repository.getPosts()
    }

    fun requestComments(postId: Int) = viewModelScope.launch {
        comments.value = repository.getComments(postId, 3)
    }
}