package br.com.recipebook.data.local

import br.com.recipebook.domain.model.AppUpdateInfoModel
import br.com.recipebook.domain.model.AppUpdateInfoModelError
import br.com.recipebook.utilitykotlin.ResultWrapper

internal class ConfigurationDataSourceLocalImpl : ConfigurationDataSourceLocal {
    private var appUpdateInfoModel: AppUpdateInfoModel? = null

    override fun getAppUpdateInfo(): ResultWrapper<AppUpdateInfoModel, AppUpdateInfoModelError> {
        return appUpdateInfoModel?.let {
            ResultWrapper.Success(it)
        } ?: ResultWrapper.Failure(AppUpdateInfoModelError.NoInformation)
    }

    override fun saveAppUpdateInfo(info: AppUpdateInfoModel) {
        appUpdateInfoModel = info
    }
}
