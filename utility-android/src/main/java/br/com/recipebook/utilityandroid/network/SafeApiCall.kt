package br.com.recipebook.utilityandroid.network

import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.NetworkError
import br.com.recipebook.utilitykotlin.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

/**
 * FIXME: This class need some improvements
 */
suspend inline fun <reified T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    crossinline apiCall: suspend () -> T
): Result<T, NetworkError> {
    return withContext(dispatcher) {
        try {
            Result.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> Result.Failure(NetworkError.UnknownError)
                is HttpException -> {
                    // val code = throwable.code()
                    // val errorResponse = convertErrorBody(throwable)
                    Result.Failure(NetworkError.HttpException)
                }
                else -> {
                    Result.Failure(NetworkError.UnknownError)
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
