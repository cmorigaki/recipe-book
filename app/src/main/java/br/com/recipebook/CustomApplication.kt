package br.com.recipebook

import android.app.Application
import br.com.recipebook.di.KoinInitializer
import br.com.recipebook.environment.BuildConfigurationProvider
import br.com.recipebook.monitoring.ActivityMonitoringWatcher
import br.com.recipebook.monitoring.MonitoringInitializer
import br.com.recipebook.monitoring.crashreport.Breadcrumb
import br.com.recipebook.monitoring.crashreport.Monitoring
import br.com.recipebook.startup.StartupJobsExecutor
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import kotlin.system.measureTimeMillis

class CustomApplication : Application() {

    private val startupJobsExecutor by inject<StartupJobsExecutor>()
    private val applicationInitWatcher by inject<ApplicationStartAnalyticsWatcher>()
    private val activityMonitoringWatcher by inject<ActivityMonitoringWatcher>()

    override fun onCreate() {
        super.onCreate()

        val startupDuration = measureTimeMillis {
            // Monitoring must be the first thing to initialize!
            MonitoringInitializer.start(this, BuildConfigurationProvider.configuration)

            // Initialize DI
            KoinInitializer.init(this)

            // Run startup jobs
            runBlocking {
                startupJobsExecutor()
            }
            Monitoring.addBreadcrumb(Breadcrumb.StartupJobFinished)
        }
        // Need to run on main thread
        applicationInitWatcher.watch(startupDuration)
        activityMonitoringWatcher.watch()
    }
}
