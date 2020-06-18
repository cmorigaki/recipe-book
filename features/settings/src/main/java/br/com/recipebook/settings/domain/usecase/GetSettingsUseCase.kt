package br.com.recipebook.settings.domain.usecase

import br.com.recipebook.navigation.intent.SettingsThemeIntent
import br.com.recipebook.settings.R
import br.com.recipebook.settings.domain.model.SettingsItemModel
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper

interface GetSettingsUseCase {
    suspend operator fun invoke(): ResultWrapper<List<SettingsItemModel>, CommonError>
}

class GetSettings : GetSettingsUseCase {
    override suspend fun invoke(): ResultWrapper<List<SettingsItemModel>, CommonError> {
        return ResultWrapper.Success(
            listOf(
                SettingsItemModel(
                    id = "1",
                    title = R.string.settings_theme,
                    navIntent = SettingsThemeIntent
                )
            )
        )
    }
}
