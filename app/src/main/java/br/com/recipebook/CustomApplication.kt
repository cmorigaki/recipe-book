package br.com.recipebook

import android.app.Application
import br.com.recipebook.di.KoinInitializer
import br.com.recipebook.startup.StartupJob
import br.com.recipebook.startup.StartupJobsExecutor
import com.facebook.drawee.backends.pipeline.Fresco
import io.sentry.android.core.SentryAndroid
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject

class CustomApplication : Application() {

    private val startupJobsExecutor by inject<StartupJobsExecutor>()
    private val applicationInitWatcher by inject<ApplicationStartAnalytics>()

    override fun onCreate() {
        super.onCreate()

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
        // Need to run on main thread
        applicationInitWatcher.watch()
    }
}

class ImageLibraryJob(private val application: Application) : StartupJob {
    override suspend fun invoke() {
        Fresco.initialize(application)
    }
}
