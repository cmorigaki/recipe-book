package br.com.recipebook.di

import br.com.recipebook.ApplicationStartAnalytics
import br.com.recipebook.BuildConfig
import org.koin.dsl.module

val applicationModule = module {
    single {
        BuildConfiguration(
            appInfo = AppInfo(
                name = BuildConfig.APPLICATION_ID,
                version = BuildConfig.VERSION_NAME,
                variant = BuildConfig.BUILD_TYPE,
                buildVariant = if (BuildConfig.DEBUG) BuildVariant.DEBUG else BuildVariant.RELEASE
            ),
            apiKeys = ApiKeys(
                sentryKey = BuildConfig.SENTRY_DSN,
                amplitudeKey = BuildConfig.AMPLITUDE_KEY
            )
        )
    }
    single {
        ApplicationStartAnalytics(
            application = get(),
            analytics = get()
        )
    }
}
