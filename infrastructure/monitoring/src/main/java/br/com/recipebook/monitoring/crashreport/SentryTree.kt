package br.com.recipebook.monitoring.crashreport

import android.util.Log
import io.sentry.core.Sentry
import io.sentry.core.SentryLevel
import timber.log.Timber

class SentryTree : Timber.Tree() {
    override fun log(
        priority: Int,
        tag: String?,
        message: String,
        t: Throwable?
    ) {
        when (priority) {
            Log.VERBOSE, Log.DEBUG, Log.INFO -> return
            Log.WARN -> Sentry.captureMessage("[$tag] $message", SentryLevel.WARNING)
            Log.ERROR, Log.ASSERT -> Sentry.captureMessage("[$tag] $message", SentryLevel.ERROR)
        }

        if (t != null) {
            Sentry.captureException(t)
        }
    }
}
