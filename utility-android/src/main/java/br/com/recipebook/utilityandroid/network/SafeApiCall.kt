package br.com.recipebook.utilityandroid.network

import br.com.recipebook.utilitykotlin.NetworkError
import br.com.recipebook.utilitykotlin.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

/**
 * FIXME: This class need some improvements
 */
suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T, NetworkError> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.Failure(NetworkError.UnknownError)
                is HttpException -> {
                    // val code = throwable.code()
                    // val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.Failure(NetworkError.HttpException)
                }
                else -> {
                    ResultWrapper.Failure(NetworkError.UnknownError)
                }
            }
        }
    }
}

//private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
//    return try {
//        throwable.response()?.errorBody()?.source()?.let {
//            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
//            moshiAdapter.fromJson(it)
//        }
//    } catch (exception: Exception) {
//        null
//    }
//}