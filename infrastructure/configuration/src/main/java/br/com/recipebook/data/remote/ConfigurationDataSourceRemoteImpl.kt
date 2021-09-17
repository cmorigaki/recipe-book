package br.com.recipebook.data.remote

import br.com.recipebook.data.remote.response.ConfigurationResponse
import br.com.recipebook.domain.model.AppUpdateInfoModel
import br.com.recipebook.domain.model.AppUpdateInfoModelError
import br.com.recipebook.utilityandroid.network.safeApiCall
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
import kotlinx.coroutines.Dispatchers
import retrofit2.http.GET

internal class ConfigurationDataSourceRemoteImpl(
    private val api: ConfigurationApi,
) : ConfigurationDataSourceRemote {
    override suspend fun getAppUpdateInfo(): Result<AppUpdateInfoModel, AppUpdateInfoModelError> {
        return safeApiCall(Dispatchers.IO) {
            api.getData()
        }.mapBoth(
            success = { configResponse ->
                configResponse.takeIf { it.minimumVersionCode != null }?.let {
                    Ok(mapConfigurationResponseToModel(it))
                } ?: Err(AppUpdateInfoModelError.NoInformation)
            },
            failure = { Err(AppUpdateInfoModelError.UnknownError) }
        )
    }

    private fun mapConfigurationResponseToModel(recipe: ConfigurationResponse) =
        AppUpdateInfoModel(
            minimumVersionCode = recipe.minimumVersionCode,
            excludedVersionCodes = recipe.excludedVersionCodes ?: emptyList(),
        )
}

internal interface ConfigurationApi {
    @GET("/configuration.json")
    suspend fun getData(): ConfigurationResponse
}
