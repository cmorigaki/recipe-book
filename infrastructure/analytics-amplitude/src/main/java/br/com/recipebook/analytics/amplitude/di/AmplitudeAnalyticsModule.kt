package br.com.recipebook.analytics.amplitude.di

import br.com.recipebook.analytics.Analytics
import br.com.recipebook.analytics.amplitude.AmplitudeAnalytics
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val amplitudeAnalyticsModule = module {
    single<Analytics> {
        AmplitudeAnalytics(
            application = androidApplication(),
            buildConfiguration = get()
        )
    }
}
