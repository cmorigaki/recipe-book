package br.com.recipebook.data.local

import br.com.recipebook.domain.model.AppUpdateInfoModel
import br.com.recipebook.domain.model.AppUpdateInfoModelError
import com.github.michaelbull.result.Result

interface ConfigurationDataSourceLocal {
    fun getAppUpdateInfo(): Result<AppUpdateInfoModel, AppUpdateInfoModelError>
    fun saveAppUpdateInfo(info: AppUpdateInfoModel)
}
