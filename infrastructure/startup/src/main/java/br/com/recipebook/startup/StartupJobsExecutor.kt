package br.com.recipebook.startup

interface StartupJobsExecutor {
    suspend operator fun invoke()
}
