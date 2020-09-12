package br.com.recipebook.monitoring.crashreport

import io.sentry.core.Sentry

sealed class Breadcrumb(val value: String) {
    object StartupJobFinished : Breadcrumb("STARTUP_JOBS_FINISHED")
    class Navigation(message: String) : Breadcrumb(message)
}

object Monitoring {
    fun addBreadcrumb(breadcrumb: Breadcrumb) {
        Sentry.addBreadcrumb(breadcrumb.value)
    }
}
