package ir.alizeyn.storytel.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alizeyn.storytel.data.model.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application), LifecycleObserver {



    public fun getPosts() = viewModelScope.launch {
        val data =repository.getPosts()
            .data

        Log.i(PostsViewModel::class.simpleName, "HERE")
        Log.i(PostsViewModel::class.simpleName, "getPosts: " + data?.size)
    }

}