package br.com.recipebook.settings.theme.domain.usecase

import androidx.appcompat.app.AppCompatDelegate
import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel
import br.com.recipebook.settings.theme.domain.repository.SettingsThemeRepository
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper

interface SetUserThemePreferenceUseCase {
    suspend operator fun invoke(theme: UserThemePreferenceModel)
}

class SetUserThemePreference(
    private val settingsThemeRepository: SettingsThemeRepository
) : SetUserThemePreferenceUseCase {
    override suspend fun invoke(theme: UserThemePreferenceModel) {
        when (theme) {
            UserThemePreferenceModel.SYSTEM ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            UserThemePreferenceModel.LIGHT ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            UserThemePreferenceModel.DARK ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        settingsThemeRepository.setCurrentTheme(theme)
    }
}
