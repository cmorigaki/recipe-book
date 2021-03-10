package br.com.recipebook.domain.model

sealed class AppUpdateInfoModelError {
    object NoInformation : AppUpdateInfoModelError()
    object UnknownError : AppUpdateInfoModelError()
}
