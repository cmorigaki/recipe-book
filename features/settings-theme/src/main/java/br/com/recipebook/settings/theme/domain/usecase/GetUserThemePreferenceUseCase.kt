package br.com.recipebook.settings.theme.domain.usecase

import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel
import br.com.recipebook.settings.theme.domain.repository.SettingsThemeRepository
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper

interface GetUserThemePreferenceUseCase {
    suspend operator fun invoke(): ResultWrapper<UserThemePreferenceModel, CommonError>
}

class GetUserThemePreference(
    private val settingsThemeRepository: SettingsThemeRepository
) : GetUserThemePreferenceUseCase {
    override suspend fun invoke(): ResultWrapper<UserThemePreferenceModel, CommonError> {
        return ResultWrapper.Success(
            settingsThemeRepository.getCurrentTheme()
        )
    }
}
