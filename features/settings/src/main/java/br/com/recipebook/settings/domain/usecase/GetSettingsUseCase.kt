package br.com.recipebook.settings.domain.usecase

import br.com.recipebook.navigation.intent.SettingsThemeIntent
import br.com.recipebook.settings.R
import br.com.recipebook.settings.domain.model.SettingsItemModel
import br.com.recipebook.utilitykotlin.CommonError
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

interface GetSettingsUseCase {
    suspend operator fun invoke(): Result<List<SettingsItemModel>, CommonError>
}

class GetSettings : GetSettingsUseCase {
    override suspend fun invoke(): Result<List<SettingsItemModel>, CommonError> {
        return Ok(
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
