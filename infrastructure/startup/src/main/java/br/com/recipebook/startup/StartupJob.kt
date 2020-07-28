package br.com.recipebook.startup

interface StartupJob {
    suspend operator fun invoke()
}
