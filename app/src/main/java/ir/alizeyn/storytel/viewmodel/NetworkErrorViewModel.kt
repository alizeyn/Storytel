package ir.alizeyn.storytel.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alizeyn.storytel.network.NetworkRetryState
import javax.inject.Inject

@HiltViewModel
class NetworkErrorViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    var retry: MutableLiveData<NetworkRetryState> = MutableLiveData(NetworkRetryState.NON)
}