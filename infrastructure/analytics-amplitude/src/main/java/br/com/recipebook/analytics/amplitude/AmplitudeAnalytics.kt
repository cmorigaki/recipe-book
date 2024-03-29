package br.com.recipebook.analytics.amplitude

import android.app.Application
import android.util.Log
import br.com.recipebook.analytics.Analytics
import br.com.recipebook.analytics.Event
import br.com.recipebook.analytics.events.LibraryInitializationEvent
import br.com.recipebook.di.BuildConfiguration
import br.com.recipebook.di.BuildVariant
import com.amplitude.api.Amplitude
import org.json.JSONObject
import timber.log.Timber
import kotlin.system.measureTimeMillis

private const val AnalyticsLibraryName = "Amplitude"

class AmplitudeAnalytics(
    private val application: Application,
    private val buildConfiguration: BuildConfiguration,
) : Analytics {

    private var isInitialized = false
    private val initLock = Any()

    private fun initialize() {
        val initTimeInMillis = measureTimeMillis {
            Amplitude.getInstance()
                .initialize(application.applicationContext, buildConfiguration.apiKeys.amplitudeKey)
                .enableForegroundTracking(application)
                .trackSessionEvents(true)
                .enableCoppaControl() // Turning off sensitive data tracking
        }

        val logLevel = when (buildConfiguration.appInfo.buildVariant) {
            BuildVariant.DEBUG -> Log.DEBUG
            BuildVariant.RELEASE -> Log.INFO
        }
        Amplitude.getInstance().setLogLevel(logLevel)

        isInitialized = true
        sendEvent(
            LibraryInitializationEvent(
                libraryName = AnalyticsLibraryName,
                timeToInitialize = initTimeInMillis
            )
        )
    }

    private fun lazyInitialization() {
        if (!isInitialized) {
            synchronized(initLock) {
                initialize()
            }
        }
    }

    override fun sendEvent(event: Event) {
        lazyInitialization()

        val attributes = event.properties.takeIf { it.isNotEmpty() }
            ?.let { eventProperties ->
                JSONObject().apply {
                    eventProperties.forEach { (key, value) ->
                        put(key, value)
                    }
                }
            }

        Timber.d("[Amplitude] Sending event $event")
        Amplitude.getInstance().logEvent(event.id, attributes)
    }
}
