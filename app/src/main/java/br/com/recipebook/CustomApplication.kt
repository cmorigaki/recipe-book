package br.com.recipebook

import android.app.Application
import br.com.recipebook.analytics.amplitude.di.amplitudeAnalyticsModule
import br.com.recipebook.coreandroid.di.coreAndroidModule
import br.com.recipebook.di.buildModule
import br.com.recipebook.navigation.di.navigationModule
import br.com.recipebook.recipecollection.di.recipeCollectionModules
import br.com.recipebook.recipedetail.di.recipeDetailModules
import br.com.recipebook.settings.di.settingsModules
import br.com.recipebook.settings.theme.di.settingsThemeModules
import com.facebook.drawee.backends.pipeline.Fresco
import io.sentry.android.core.SentryAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Crash report must be the first thing to initialize!
        if (!BuildConfig.DEBUG) {
            SentryAndroid.init(this)
        }

        initDI()

        Fresco.initialize(this) // FIXME create abstraction layer and do a lazy init
    }

    private fun initDI() {
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@CustomApplication)
            // declare modules
            modules(
                buildModule +
                    coreAndroidModule +
                    navigationModule +
                    recipeCollectionModules +
                    recipeDetailModules +
                    settingsModules +
                    settingsThemeModules +
                    amplitudeAnalyticsModule
            )
        }
    }
}
