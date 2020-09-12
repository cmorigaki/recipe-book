package br.com.recipebook.monitoring

import android.app.Activity
import android.app.Application
import android.os.Bundle
import br.com.recipebook.monitoring.crashreport.Breadcrumb
import br.com.recipebook.monitoring.crashreport.Monitoring
import timber.log.Timber

class ActivityMonitoringWatcher(
    private val application: Application
) : Application.ActivityLifecycleCallbacks {
    fun watch() {
        application.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?
    ) {
        // Nothing to do
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
        val message = "[Nav] onActivityResumed ${activity.javaClass.simpleName}"
        Monitoring.addBreadcrumb(Breadcrumb.Navigation(message))
        Timber.i(message)
    }
}
