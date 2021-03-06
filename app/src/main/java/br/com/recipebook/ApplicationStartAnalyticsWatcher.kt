package br.com.recipebook

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import br.com.recipebook.analytics.Analytics
import br.com.recipebook.analytics.AppStartEvent
import br.com.recipebook.analytics.InitMode

class ApplicationStartAnalyticsWatcher(
    private val application: Application,
    private val analytics: Analytics
) : Application.ActivityLifecycleCallbacks {
    private var firstActivityCreated = false
    private var hasSavedState = false

    fun watch(duration: Long) {
        application.registerActivityLifecycleCallbacks(this)
        Handler(application.mainLooper).post {
            if (firstActivityCreated) {
                if (hasSavedState) {
                    analytics.sendEvent(
                        AppStartEvent(InitMode.LUKEWARM_START, duration)
                    )
                } else {
                    analytics.sendEvent(
                        AppStartEvent(InitMode.COLD_START, duration)
                    )
                }
            }
        }
    }

    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?
    ) {
        if (firstActivityCreated) {
            return
        }
        firstActivityCreated = true
        hasSavedState = savedInstanceState != null
    }

    override fun onActivityPaused(activity: Activity) {
        // Nothing to do
    }

    override fun onActivityStarted(activity: Activity) {
        // Nothing to do
    }

    override fun onActivityDestroyed(activity: Activity) {
        // Nothing to do
    }

    override fun onActivitySaveInstanceState(
        activity: Activity,
        outState: Bundle
    ) {
        // Nothing to do
    }

    override fun onActivityStopped(activity: Activity) {
        // Nothing to do
    }

    override fun onActivityResumed(activity: Activity) {
        // Nothing to do
    }
}
