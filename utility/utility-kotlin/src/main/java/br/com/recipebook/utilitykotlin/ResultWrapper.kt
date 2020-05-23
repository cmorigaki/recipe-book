package br.com.recipebook.utilitykotlin

sealed class ResultWrapper<out S, out E> {
    data class Success<S>(val data: S) : ResultWrapper<S, Nothing>()
    data class Failure<E>(val error: E) : ResultWrapper<Nothing, E>()

    inline fun <T> mapSuccess(transform: (S) -> T): ResultWrapper<T, E> = when (this) {
        is Success -> Success(transform(data))
        is Failure -> Failure(error)
    }

    inline fun <T> mapError(transform: (E) -> T): ResultWrapper<S, T> = when (this) {
        is Success -> Success(data)
        is Failure -> Failure(transform(error))
    }
}