package br.com.recipebook.data.local

import br.com.recipebook.domain.model.AppUpdateInfoModel
import br.com.recipebook.domain.model.AppUpdateInfoModelError
import br.com.recipebook.utilitykotlin.ResultWrapper

interface ConfigurationDataSourceLocal {
    fun getAppUpdateInfo(): ResultWrapper<AppUpdateInfoModel, AppUpdateInfoModelError>
    fun saveAppUpdateInfo(info: AppUpdateInfoModel)
}
