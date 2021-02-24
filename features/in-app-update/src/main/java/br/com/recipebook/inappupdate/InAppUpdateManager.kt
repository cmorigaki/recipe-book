package br.com.recipebook.inappupdate

import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.CompletableDeferred

private const val TAG = "InAppUpdateManager"

suspend fun FragmentActivity.requestInAppUpdate(
    appUpdateType: Int,
    requestCode: Int
): InAppUpdateResult {
    val fragment = supportFragmentManager.findFragmentByTag(TAG) as? InAppUpdateHeadlessFragment
    return if (fragment == null) {
        val inAppUpdate = InAppUpdateHeadlessFragment.newInstance(
            appUpdateType = appUpdateType,
            requestCode = requestCode
        ).apply {
            completableDeferred = CompletableDeferred()
        }
        supportFragmentManager.beginTransaction().add(inAppUpdate, TAG).commitAllowingStateLoss()
        inAppUpdate.completableDeferred.await()
    } else {
        fragment.completableDeferred.await()
    }
}

sealed class InAppUpdateResult {
    object UpdateCompleted : InAppUpdateResult()
    object UpdateNotAvailable : InAppUpdateResult()
    object UpdateFailed : InAppUpdateResult()
}
