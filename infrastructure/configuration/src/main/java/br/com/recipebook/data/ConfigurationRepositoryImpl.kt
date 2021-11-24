package br.com.recipebook.data

import br.com.recipebook.data.local.ConfigurationDataSourceLocal
import br.com.recipebook.data.remote.ConfigurationDataSourceRemote
import br.com.recipebook.domain.ConfigurationRepository
import br.com.recipebook.domain.model.AppUpdateInfoModel
import br.com.recipebook.domain.model.AppUpdateInfoModelError
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
import com.github.michaelbull.result.onSuccess

internal class ConfigurationRepositoryImpl(
    private val dataSourceRemote: ConfigurationDataSourceRemote,
    private val dataSourceLocal: ConfigurationDataSourceLocal,
) : ConfigurationRepository {
    override suspend fun getAppUpdateInfo(): Result<AppUpdateInfoModel, AppUpdateInfoModelError> {
        return dataSourceLocal.getAppUpdateInfo()
            .mapBoth(
                success = { Ok(it) },
                failure = {
                    dataSourceRemote.getAppUpdateInfo()
                        .onSuccess { dataSourceLocal.saveAppUpdateInfo(it) }
                }
            )
    }
}
