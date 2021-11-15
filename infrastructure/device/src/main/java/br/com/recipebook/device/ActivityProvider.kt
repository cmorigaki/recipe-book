package br.com.recipebook.device

import android.app.Activity
import android.app.Application
import android.os.Bundle

class ActivityProvider(application: Application) {
    var activeActivity: Activity? = null

    init {
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(
                activity: Activity,
                savedInstanceState: Bundle?
            ) = Unit

            override fun onActivityStarted(activity: Activity) = Unit

            override fun onActivityResumed(activity: Activity) {
                activeActivity = activity
            }

            override fun onActivityPaused(activity: Activity) {
                activeActivity = null
            }

            override fun onActivityStopped(activity: Activity) = Unit

            override fun onActivitySaveInstanceState(
                activity: Activity,
                outState: Bundle
            ) = Unit

            override fun onActivityDestroyed(activity: Activity) = Unit
        })
    }
}
