package br.com.recipebook.di

import br.com.recipebook.startup.StartupJobsExecutor
import br.com.recipebook.startup.StartupJobsExecutorImpl
import org.koin.dsl.module

val startupModule = module {
    single<StartupJobsExecutor> {
        StartupJobsExecutorImpl(
            listStartupJobsExecutors = getAll()
        )
    }
}
