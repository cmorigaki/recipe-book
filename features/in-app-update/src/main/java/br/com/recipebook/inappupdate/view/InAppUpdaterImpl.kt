package br.com.recipebook.inappupdate.view

import androidx.fragment.app.FragmentActivity
import br.com.recipebook.device.ActivityProvider
import br.com.recipebook.inappupdate.InAppUpdateResult
import br.com.recipebook.inappupdate.domain.InAppUpdater
import br.com.recipebook.inappupdate.requestInAppUpdate
import com.google.android.play.core.install.model.AppUpdateType

private const val REQUEST_CODE = 1292

internal class InAppUpdaterImpl(
    private val activityProvider: ActivityProvider,
) : InAppUpdater {
    override suspend fun invoke(): InAppUpdateResult {
        return (activityProvider.activeActivity as? FragmentActivity)?.requestInAppUpdate(
            appUpdateType = AppUpdateType.IMMEDIATE,
            requestCode = REQUEST_CODE
        ) ?: InAppUpdateResult.UpdateFailed
    }
}
