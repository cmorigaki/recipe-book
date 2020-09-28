package br.com.recipebook.startup

import br.com.recipebook.utilitykotlin.coroutines.DispatcherProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class StartupJobsExecutorImpl(
    private val listStartupJobsExecutors: List<StartupJob>,
    private val dispatcherProvider: DispatcherProvider
) : StartupJobsExecutor {
    override suspend operator fun invoke() {
        withContext(dispatcherProvider.default()) {
            listStartupJobsExecutors.forEach { launch { it() } }
        }
    }
}
