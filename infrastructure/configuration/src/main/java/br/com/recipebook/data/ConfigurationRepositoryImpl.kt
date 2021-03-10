package br.com.recipebook.data

import br.com.recipebook.data.local.ConfigurationDataSourceLocal
import br.com.recipebook.data.remote.ConfigurationDataSourceRemote
import br.com.recipebook.domain.ConfigurationRepository
import br.com.recipebook.domain.model.AppUpdateInfoModel
import br.com.recipebook.domain.model.AppUpdateInfoModelError
import br.com.recipebook.utilitykotlin.ResultWrapper

internal class ConfigurationRepositoryImpl(
    private val dataSourceRemote: ConfigurationDataSourceRemote,
    private val dataSourceLocal: ConfigurationDataSourceLocal,
) : ConfigurationRepository {
    override suspend fun getAppUpdateInfo(): ResultWrapper<AppUpdateInfoModel, AppUpdateInfoModelError> {
        return when (val localResult = dataSourceLocal.getAppUpdateInfo()) {
            is ResultWrapper.Success -> localResult
            is ResultWrapper.Failure -> {
                when (val remoteResult = dataSourceRemote.getAppUpdateInfo()) {
                    is ResultWrapper.Success -> {
                        dataSourceLocal.saveAppUpdateInfo(remoteResult.data)
                        ResultWrapper.Success(remoteResult.data)
                    }
                    is ResultWrapper.Failure -> remoteResult
                }
            }
        }
    }
}
