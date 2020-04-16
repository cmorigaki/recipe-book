package br.com.recipebook.utilitykotlin

sealed class ResultWrapper<out S, out E> {
    data class Success<S>(val data: S) : ResultWrapper<S, Nothing>()
    data class Failure<E>(val error: E) : ResultWrapper<Nothing, E>()
}