package br.com.recipebook.settings.theme.domain.usecase

import androidx.appcompat.app.AppCompatDelegate
import br.com.recipebook.analytics.Analytics
import br.com.recipebook.settings.theme.analytics.SwitchThemeEvent
import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel
import br.com.recipebook.settings.theme.domain.repository.SettingsThemeRepository

interface SetUserThemePreferenceUseCase {
    suspend operator fun invoke(theme: UserThemePreferenceModel)
}

class SetUserThemePreference(
    private val settingsThemeRepository: SettingsThemeRepository,
    private val analytics: Analytics,
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
        analytics.sendEvent(SwitchThemeEvent(theme))
    }
}
