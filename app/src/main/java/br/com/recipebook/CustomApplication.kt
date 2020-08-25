package br.com.recipebook

import android.app.Application
import br.com.recipebook.di.KoinInitializer
import br.com.recipebook.startup.StartupJobsExecutor
import io.sentry.android.core.SentryAndroid
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import kotlin.system.measureTimeMillis

class CustomApplication : Application() {

    private val startupJobsExecutor by inject<StartupJobsExecutor>()
    private val applicationInitWatcher by inject<ApplicationStartAnalytics>()

    override fun onCreate() {
        super.onCreate()

        val startupDuration = measureTimeMillis {
            // Crash report must be the first thing to initialize!
            if (!BuildConfig.DEBUG) {
                SentryAndroid.init(this)
            }

            // Initialize DI
            KoinInitializer.init(this)

            // Run startup jobs
            runBlocking {
                startupJobsExecutor()
            }
        }
        // Need to run on main thread
        applicationInitWatcher.watch(startupDuration)
    }
}
