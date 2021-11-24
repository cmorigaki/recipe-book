package br.com.recipebook.domain

import br.com.recipebook.domain.model.AppUpdateInfoModel
import br.com.recipebook.domain.model.AppUpdateInfoModelError
import com.github.michaelbull.result.Result

interface ConfigurationRepository {
    suspend fun getAppUpdateInfo(): Result<AppUpdateInfoModel, AppUpdateInfoModelError>
}
