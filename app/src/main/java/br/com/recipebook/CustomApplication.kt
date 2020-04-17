package br.com.recipebook

import android.app.Application
import br.com.recipebook.coreandroid.di.coreAndroidModule
import br.com.recipebook.recipecollection.di.recipeCollectionModules
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
                        recipeCollectionModules
            )
        }
    }
}