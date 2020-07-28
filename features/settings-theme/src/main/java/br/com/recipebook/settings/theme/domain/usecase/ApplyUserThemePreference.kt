package br.com.recipebook.settings.theme.domain.usecase

import androidx.appcompat.app.AppCompatDelegate
import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel
import br.com.recipebook.settings.theme.domain.repository.SettingsThemeRepository
import br.com.recipebook.startup.StartupJob

interface ApplyUserThemePreferenceUseCase : StartupJob {
    override suspend operator fun invoke()
}

class ApplyUserThemePreference(
    private val settingsThemeRepository: SettingsThemeRepository
) : ApplyUserThemePreferenceUseCase {
    override suspend fun invoke() {
        when (settingsThemeRepository.getCurrentTheme()) {
            UserThemePreferenceModel.SYSTEM ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            UserThemePreferenceModel.LIGHT ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            UserThemePreferenceModel.DARK ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}
