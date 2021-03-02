package br.com.recipebook.di

import android.app.Application
import br.com.recipebook.analytics.amplitude.di.amplitudeAnalyticsModule
import br.com.recipebook.coreandroid.di.coreAndroidModule
import br.com.recipebook.device.di.deviceModules
import br.com.recipebook.image.di.imageModule
import br.com.recipebook.inappupdate.di.inAppUpdateModules
import br.com.recipebook.monitoring.di.monitoringModule
import br.com.recipebook.navigation.di.navigationModule
import br.com.recipebook.recipecollection.di.recipeCollectionModules
import br.com.recipebook.recipedetail.di.recipeDetailModules
import br.com.recipebook.settings.di.settingsModules
import br.com.recipebook.settings.theme.di.settingsThemeModules
import br.com.recipebook.startup.di.startupModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinInitializer {
    fun init(application: Application) {
        startKoin {
            // declare used Android context
            androidContext(application)
            // declare modules
            modules(
                applicationModule +
                    imageModule +
                    coreAndroidModule +
                    startupModule +
                    navigationModule +
                    recipeCollectionModules +
                    recipeDetailModules +
                    settingsModules +
                    settingsThemeModules +
                    amplitudeAnalyticsModule +
                    configurationModules +
                    inAppUpdateModules +
                    monitoringModule +
                    deviceModules
            )
        }
    }
}
