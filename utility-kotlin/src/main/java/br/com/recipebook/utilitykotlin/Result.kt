package br.com.recipebook.utilitykotlin

sealed class Result<S, E> {
    data class Success<S, E>(val data: S) : Result<S, E>()
    data class Failure<S, E>(val error: E) : Result<S, E>()
}