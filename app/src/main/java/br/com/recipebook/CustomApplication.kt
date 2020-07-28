package br.com.recipebook

import android.app.Application
import br.com.recipebook.analytics.amplitude.di.amplitudeAnalyticsModule
import br.com.recipebook.coreandroid.di.coreAndroidModule
import br.com.recipebook.di.buildModule
import br.com.recipebook.di.startupModule
import br.com.recipebook.navigation.di.navigationModule
import br.com.recipebook.recipecollection.di.recipeCollectionModules
import br.com.recipebook.recipedetail.di.recipeDetailModules
import br.com.recipebook.settings.di.settingsModules
import br.com.recipebook.settings.theme.di.settingsThemeModules
import br.com.recipebook.startup.StartupJobsExecutor
import com.facebook.drawee.backends.pipeline.Fresco
import io.sentry.android.core.SentryAndroid
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Crash report must be the first thing to initialize!
        if (!BuildConfig.DEBUG) {
            SentryAndroid.init(this)
        }

        initDI().apply {
            executeStartUpJobs(koin)
        }
        Fresco.initialize(this) // FIXME create abstraction layer and do a lazy init
    }

    private fun initDI(): KoinApplication {
        return startKoin {
            // declare used Android context
            androidContext(this@CustomApplication)
            // declare modules
            modules(
                buildModule +
                    coreAndroidModule +
                    startupModule +
                    navigationModule +
                    recipeCollectionModules +
                    recipeDetailModules +
                    settingsModules +
                    settingsThemeModules +
                    amplitudeAnalyticsModule
            )
        }
    }

    private fun executeStartUpJobs(koin: Koin) {
        val startupJobsExecutor = koin.get<StartupJobsExecutor>()
        runBlocking {
            startupJobsExecutor()
        }
    }
}
