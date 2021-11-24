package br.com.recipebook.data.local

import br.com.recipebook.domain.model.AppUpdateInfoModel
import br.com.recipebook.domain.model.AppUpdateInfoModelError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

internal class ConfigurationDataSourceLocalImpl : ConfigurationDataSourceLocal {
    private var appUpdateInfoModel: AppUpdateInfoModel? = null

    override fun getAppUpdateInfo(): Result<AppUpdateInfoModel, AppUpdateInfoModelError> {
        return appUpdateInfoModel?.let {
            Ok(it)
        } ?: Err(AppUpdateInfoModelError.NoInformation)
    }

    override fun saveAppUpdateInfo(info: AppUpdateInfoModel) {
        appUpdateInfoModel = info
    }
}
