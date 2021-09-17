package br.com.recipebook.inappupdate.domain

import br.com.recipebook.analytics.Analytics
import br.com.recipebook.di.BuildConfiguration
import br.com.recipebook.domain.ConfigurationRepository
import br.com.recipebook.domain.model.AppUpdateInfoModel
import br.com.recipebook.inappupdate.InAppUpdateResult
import br.com.recipebook.inappupdate.analytics.InAppUpdateEvent
import br.com.recipebook.monitoring.crashreport.Breadcrumb
import br.com.recipebook.monitoring.crashreport.Monitoring
import com.github.michaelbull.result.mapBoth

interface CheckInAppUpdateUseCase {
    suspend operator fun invoke(): Boolean
}

internal class CheckInAppUpdate(
    private val buildConfiguration: BuildConfiguration,
    private val configurationRepository: ConfigurationRepository,
    private val inAppUpdater: InAppUpdater,
    private val analytics: Analytics,
) : CheckInAppUpdateUseCase {
    override suspend fun invoke(): Boolean {
        val shouldUpdate = configurationRepository.getAppUpdateInfo().mapBoth(
            success = { shouldAskUpdate(it) },
            failure = { true }
        )

        return if (shouldUpdate) {
            Monitoring.addBreadcrumb(Breadcrumb.StartingInAppUpdate)
            val result = inAppUpdater()
            sendEvent(shouldUpdate, result)
            result !is InAppUpdateResult.UpdateFailed
        } else {
            sendEvent(shouldUpdate, null)
            true
        }
    }

    private fun shouldAskUpdate(info: AppUpdateInfoModel): Boolean {
        return buildConfiguration.appInfo.versionCode.let { currentVersionCode ->
            (currentVersionCode < info.minimumVersionCode ?: 0) ||
                info.excludedVersionCodes.contains(currentVersionCode)
        }
    }

    private fun sendEvent(
        shouldUpdate: Boolean,
        updateStatus: InAppUpdateResult?
    ) {
        val updateResult = when (updateStatus) {
            null -> null
            is InAppUpdateResult.UpdateNotAvailable -> "Not available"
            is InAppUpdateResult.UpdateCompleted -> "Completed"
            is InAppUpdateResult.UpdateFailed -> "Failed"
        }
        analytics.sendEvent(
            InAppUpdateEvent(
                shouldUpdate = shouldUpdate,
                updateStatus = updateResult,
                currentVersionCode = buildConfiguration.appInfo.versionCode
            )
        )
    }
}
