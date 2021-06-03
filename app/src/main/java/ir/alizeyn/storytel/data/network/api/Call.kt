package ir.alizeyn.storytel.data.network.api

import ir.alizeyn.storytel.data.network.model.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Call {

    suspend fun <T> safeCall(dispatcher: CoroutineDispatcher = Dispatchers.IO,
                             apiCall: suspend () -> T): Response<T> {

        return withContext(dispatcher) {
            try {
                val result = apiCall.invoke()
                Response.Success(result)
            } catch (throwable: Exception) {
                throwable.printStackTrace()
                Response.Error(throwable.message)
            }
        }
    }
}