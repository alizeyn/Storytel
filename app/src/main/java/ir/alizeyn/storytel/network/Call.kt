package ir.alizeyn.storytel.network

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
                Response.Error(throwable.message)
            }
        }
    }
}