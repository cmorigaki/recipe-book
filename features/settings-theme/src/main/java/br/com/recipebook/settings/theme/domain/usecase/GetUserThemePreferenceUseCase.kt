package br.com.recipebook.settings.theme.domain.usecase

import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel
import br.com.recipebook.settings.theme.domain.repository.SettingsThemeRepository
import br.com.recipebook.utilitykotlin.CommonError
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

interface GetUserThemePreferenceUseCase {
    suspend operator fun invoke(): Result<UserThemePreferenceModel, CommonError>
}

class GetUserThemePreference(
    private val settingsThemeRepository: SettingsThemeRepository
) : GetUserThemePreferenceUseCase {
    override suspend fun invoke(): Result<UserThemePreferenceModel, CommonError> {
        return Ok(settingsThemeRepository.getCurrentTheme())
    }
}
