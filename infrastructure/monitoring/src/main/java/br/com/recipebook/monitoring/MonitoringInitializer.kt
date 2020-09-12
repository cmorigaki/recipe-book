package br.com.recipebook.monitoring

import android.content.Context
import br.com.recipebook.di.BuildConfiguration
import br.com.recipebook.di.BuildVariant
import br.com.recipebook.monitoring.crashreport.SentryTree
import io.sentry.android.core.SentryAndroid
import timber.log.Timber
import timber.log.Timber.DebugTree

object MonitoringInitializer {
    fun start(
        context: Context,
        buildConfiguration: BuildConfiguration
    ) {
        // Crash report must be the first thing to initialize!
        if (buildConfiguration.appInfo.buildVariant == BuildVariant.RELEASE) {
            SentryAndroid.init(context)
        }

        if (buildConfiguration.appInfo.buildVariant == BuildVariant.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(SentryTree())
        }
    }
}
