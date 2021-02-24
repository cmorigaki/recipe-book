package br.com.recipebook.domain

import br.com.recipebook.domain.model.AppUpdateInfoModel
import br.com.recipebook.domain.model.AppUpdateInfoModelError
import br.com.recipebook.utilitykotlin.ResultWrapper

interface ConfigurationRepository {
    suspend fun getAppUpdateInfo(): ResultWrapper<AppUpdateInfoModel, AppUpdateInfoModelError>
}
