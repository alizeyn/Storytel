package ir.alizeyn.storytel.presentation.comment

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alizeyn.storytel.data.network.model.Response
import ir.alizeyn.storytel.data.repository.comment.CommentRepository
import ir.alizeyn.storytel.domain.StorytelComment
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val repository: CommentRepository
) : ViewModel(), LifecycleObserver {

    var comments: MutableLiveData<Response<List<StorytelComment>>> = MutableLiveData()

    fun requestComments(postId: Int) = viewModelScope.launch {
        comments.value = repository.getComments(postId)
    }
}