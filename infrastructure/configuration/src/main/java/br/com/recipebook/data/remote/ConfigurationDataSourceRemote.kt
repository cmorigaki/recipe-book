package br.com.recipebook.data.remote

import br.com.recipebook.domain.model.AppUpdateInfoModel
import br.com.recipebook.domain.model.AppUpdateInfoModelError
import com.github.michaelbull.result.Result

interface ConfigurationDataSourceRemote {
    suspend fun getAppUpdateInfo(): Result<AppUpdateInfoModel, AppUpdateInfoModelError>
}
