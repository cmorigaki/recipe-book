package br.com.recipebook.monitoring.crashreport

import io.sentry.Sentry

sealed class Breadcrumb(val value: String) {
    object StartupJobFinished : Breadcrumb("Startup job finished")
    class Navigation(message: String) : Breadcrumb(message)
    object StartingInAppUpdate : Breadcrumb("Starting in-app update")
}

object Monitoring {
    fun addBreadcrumb(breadcrumb: Breadcrumb) {
        Sentry.addBreadcrumb(breadcrumb.value)
    }
}
