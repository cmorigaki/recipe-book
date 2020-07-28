package br.com.recipebook.startup

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class StartupJobsExecutorImpl(
    private val listStartupJobsExecutors: List<StartupJob>
) : StartupJobsExecutor {
    override suspend operator fun invoke() {
        withContext(Dispatchers.Default) {
            listStartupJobsExecutors.forEach { launch { it() } }
        }
    }
}
