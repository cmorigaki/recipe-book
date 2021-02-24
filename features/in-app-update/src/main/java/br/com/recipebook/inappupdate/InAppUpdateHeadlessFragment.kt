package br.com.recipebook.inappupdate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.recipebook.utilityandroid.view.putSafeArgs
import br.com.recipebook.utilityandroid.view.safeArgs
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.startUpdateFlowForResult
import kotlinx.coroutines.CompletableDeferred

internal class InAppUpdateHeadlessFragment : Fragment() {
    private val safeArgs by safeArgs<InAppUpdateSafeArgs>()

    lateinit var completableDeferred: CompletableDeferred<InAppUpdateResult>

    override fun onResume() {
        super.onResume()
        checkInAppUpdate()
    }

    private fun checkInAppUpdate() {
        // Creates instance of the manager.
        val appUpdateManager = AppUpdateManagerFactory.create(requireContext())

        // Returns an intent object that you use to check for an update.
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (isUpdateAvailable(appUpdateInfo.updateAvailability()) &&
                appUpdateInfo.isUpdateTypeAllowed(safeArgs.appUpdateType)
            ) {
                // Request the update.
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    safeArgs.appUpdateType,
                    this,
                    safeArgs.requestCode
                )
            } else {
                completeAndRemoveFragment(InAppUpdateResult.UpdateNotAvailable)
            }
        }
    }

    private fun isUpdateAvailable(updateAvailability: Int): Boolean {
        return updateAvailability == UpdateAvailability.UPDATE_AVAILABLE ||
            updateAvailability == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == safeArgs.requestCode) {
            if (resultCode != AppCompatActivity.RESULT_OK) {
                // If the update is cancelled or fails,
                // you can request to start the update again.
                completeAndRemoveFragment(InAppUpdateResult.UpdateFailed)
            } else {
                completeAndRemoveFragment(InAppUpdateResult.UpdateCompleted)
            }
        }
    }

    private fun completeAndRemoveFragment(result: InAppUpdateResult) {
        if (::completableDeferred.isInitialized) {
            completableDeferred.complete(result)
        }
        removeThisFragment()
    }

    private fun removeThisFragment() {
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commitAllowingStateLoss()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::completableDeferred.isInitialized && completableDeferred.isActive) {
            completableDeferred.cancel()
        }
    }

    companion object {
        fun newInstance(
            appUpdateType: Int,
            requestCode: Int
        ) = InAppUpdateHeadlessFragment()
            .putSafeArgs(
                InAppUpdateSafeArgs(
                    appUpdateType = appUpdateType,
                    requestCode = requestCode,
                )
            )
    }
}
