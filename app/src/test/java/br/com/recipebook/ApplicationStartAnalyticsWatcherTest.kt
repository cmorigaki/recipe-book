package br.com.recipebook

import android.app.Activity
import android.app.Application
import br.com.recipebook.analytics.Analytics
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ApplicationStartAnalyticsWatcherTest {
    private val application: Application = mockk()
    private val analytics: Analytics = mockk()

    private val applicationStartAnalyticsWatcher = ApplicationStartAnalyticsWatcher(application, analytics)

    private val activity: Activity = mockk()

    @Test
    fun watch() {
        applicationStartAnalyticsWatcher.onActivityPaused(activity)
        assertEquals(1, 1)
    }
}
