package br.com.recipebook

import android.app.Application
import br.com.recipebook.coreandroid.di.coreAndroidModule
import br.com.recipebook.navigation.di.navigationModule
import br.com.recipebook.recipecollection.di.recipeCollectionModules
import br.com.recipebook.recipedetail.di.recipeDetailModules
import br.com.recipebook.settings.di.settingsModules
import com.facebook.drawee.backends.pipeline.Fresco
import io.sentry.android.core.SentryAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@CustomApplication)
            // declare modules
            modules(
                coreAndroidModule +
                        navigationModule +
                        recipeCollectionModules +
                        recipeDetailModules +
                        settingsModules
            )
        }
        Fresco.initialize(this)

        if (!BuildConfig.DEBUG) {
            SentryAndroid.init(this)
        }
    }
}