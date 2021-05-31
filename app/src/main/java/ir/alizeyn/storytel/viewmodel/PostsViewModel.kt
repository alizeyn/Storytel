package ir.alizeyn.storytel.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alizeyn.storytel.data.model.Repository
import ir.alizeyn.storytel.data.model.domain.StorytelPost
import ir.alizeyn.storytel.network.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application), LifecycleObserver {

    var posts: MutableLiveData<Response<List<StorytelPost>>> = MutableLiveData()

    fun getPosts() {
        requestPosts()
    }

    private fun requestPosts() = viewModelScope.launch {

        posts.value = repository.getPosts()
        val data = repository.getPosts()
            .data

        Log.i(PostsViewModel::class.simpleName, "HERE")
        Log.i(PostsViewModel::class.simpleName, "getPosts: " + data?.size)
    }

}