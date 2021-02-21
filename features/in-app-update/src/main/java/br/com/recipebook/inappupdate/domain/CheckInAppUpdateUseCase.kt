package br.com.recipebook.inappupdate.domain

interface CheckInAppUpdateUseCase {
    operator fun invoke()
}

internal class CheckInAppUpdate() : CheckInAppUpdateUseCase {
    override fun invoke() {
        TODO("Not yet implemented")
    }
}
