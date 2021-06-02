package ir.alizeyn.storytel.presentation.comment

import androidx.lifecycle.*
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

    private var comments: MutableLiveData<Response<List<StorytelComment>>> = MutableLiveData()
    val commentList: LiveData<Response<List<StorytelComment>>>
        get() = comments

    fun requestComments(postId: Int) = viewModelScope.launch {
        comments.value = repository.getComments(postId)
    }
}