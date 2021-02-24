package br.com.recipebook.data.remote

import br.com.recipebook.domain.model.AppUpdateInfoModel
import br.com.recipebook.domain.model.AppUpdateInfoModelError
import br.com.recipebook.utilitykotlin.ResultWrapper

interface ConfigurationDataSourceRemote {
    suspend fun getAppUpdateInfo(): ResultWrapper<AppUpdateInfoModel, AppUpdateInfoModelError>
}
