package br.com.recipebook.environment

import br.com.recipebook.BuildConfig
import br.com.recipebook.di.ApiKeys
import br.com.recipebook.di.AppInfo
import br.com.recipebook.di.BuildConfiguration
import br.com.recipebook.di.BuildVariant

/**
 * This object is needed because we need it before DI framework is initiated (for monitoring start)
 */
object BuildConfigurationProvider {
    val configuration = BuildConfiguration(
        appInfo = AppInfo(
            name = BuildConfig.APPLICATION_ID,
            version = BuildConfig.VERSION_NAME,
            versionCode = BuildConfig.VERSION_CODE,
            variant = BuildConfig.BUILD_TYPE,
            buildVariant = if (BuildConfig.DEBUG) BuildVariant.DEBUG else BuildVariant.RELEASE
        ),
        apiKeys = ApiKeys(
            sentryKey = BuildConfig.SENTRY_DSN,
            amplitudeKey = BuildConfig.AMPLITUDE_KEY
        )
    )
}
