package br.com.recipebook.utilitykotlin

sealed class Result<out S, out E> {
    data class Success<S>(val data: S) : Result<S, Nothing>()
    data class Failure<E>(val error: E) : Result<Nothing, E>()
}