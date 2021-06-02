package ir.alizeyn.storytel.presentation.network

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alizeyn.storytel.data.network.NetworkRetryState
import javax.inject.Inject

@HiltViewModel
class NetworkErrorViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    var retry: MutableLiveData<NetworkRetryState> = MutableLiveData(NetworkRetryState.NON)
}