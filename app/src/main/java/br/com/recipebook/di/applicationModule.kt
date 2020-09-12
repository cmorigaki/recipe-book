package br.com.recipebook.di

import br.com.recipebook.ApplicationStartAnalyticsWatcher
import br.com.recipebook.environment.BuildConfigurationProvider
import org.koin.dsl.module

val applicationModule = module {
    single {
        BuildConfigurationProvider.configuration
    }
    single {
        ApplicationStartAnalyticsWatcher(
            application = get(),
            analytics = get()
        )
    }
}
