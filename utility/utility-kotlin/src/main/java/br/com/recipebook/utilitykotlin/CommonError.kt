package br.com.recipebook.utilitykotlin

sealed class CommonError
sealed class NetworkError : CommonError() {
    object UnknownError : NetworkError()
    object HttpException : NetworkError()
}
object GenericError : CommonError()
