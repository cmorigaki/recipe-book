package br.com.recipebook.data.remote

import br.com.recipebook.data.remote.response.ConfigurationResponse
import br.com.recipebook.domain.model.AppUpdateInfoModel
import br.com.recipebook.domain.model.AppUpdateInfoModelError
import br.com.recipebook.utilityandroid.network.safeApiCall
import br.com.recipebook.utilitykotlin.ResultWrapper
import kotlinx.coroutines.Dispatchers
import retrofit2.http.GET

internal class ConfigurationDataSourceRemoteImpl(
    private val api: ConfigurationApi,
) : ConfigurationDataSourceRemote {
    override suspend fun getAppUpdateInfo(): ResultWrapper<AppUpdateInfoModel, AppUpdateInfoModelError> {
        val result = safeApiCall(Dispatchers.IO) {
            api.getData()
        }
        return when (result) {
            is ResultWrapper.Success -> {
                result.data.takeIf {
                    it.minimumVersionCode != null &&
                        !it.excludedVersionCodes.isNullOrEmpty() &&
                        it.appStoreVersion != null
                }?.let {
                    ResultWrapper.Success(mapConfigurationResponseToModel(it))
                } ?: ResultWrapper.Failure(AppUpdateInfoModelError.NoInformation)
            }
            is ResultWrapper.Failure -> ResultWrapper.Failure(AppUpdateInfoModelError.UnknownError)
        }
    }

    private fun mapConfigurationResponseToModel(recipe: ConfigurationResponse) =
        AppUpdateInfoModel(
            minimumVersionCode = recipe.minimumVersionCode,
            excludedVersionCodes = recipe.excludedVersionCodes ?: emptyList(),
            appStoreVersion = recipe.appStoreVersion,
        )
}

internal interface ConfigurationApi {
    @GET("/configuration.json")
    suspend fun getData(): ConfigurationResponse
}
