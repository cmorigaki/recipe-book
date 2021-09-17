package br.com.recipebook.utilityandroid.network

import br.com.recipebook.utilitykotlin.NetworkError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
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
): Result<T, NetworkError> {
    return withContext(dispatcher) {
        try {
            Ok(apiCall())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> Err(NetworkError.UnknownError)
                is HttpException -> {
                    Err(NetworkError.HttpException)
                }
                else -> {
                    Err(NetworkError.UnknownError)
                }
            }
        }
    }
}
