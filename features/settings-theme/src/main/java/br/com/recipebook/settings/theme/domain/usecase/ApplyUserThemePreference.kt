package br.com.recipebook.settings.theme.domain.usecase

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import br.com.recipebook.analytics.Analytics
import br.com.recipebook.settings.theme.analytics.ThemeConfigurationEvent
import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel
import br.com.recipebook.settings.theme.domain.repository.SettingsThemeRepository
import br.com.recipebook.startup.StartupJob
import br.com.recipebook.utilityandroid.view.theme.isDarkThemeOn

interface ApplyUserThemePreferenceUseCase : StartupJob {
    override suspend operator fun invoke()
}

class ApplyUserThemePreference(
    private val settingsThemeRepository: SettingsThemeRepository,
    private val analytics: Analytics,
    private val context: Context,
) : ApplyUserThemePreferenceUseCase {
    override suspend fun invoke() {
        val selectedTheme = settingsThemeRepository.getCurrentTheme()
        sendThemeConfiguration(selectedTheme)

        when (selectedTheme) {
            UserThemePreferenceModel.SYSTEM ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            UserThemePreferenceModel.LIGHT ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            UserThemePreferenceModel.DARK ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun sendThemeConfiguration(selectedTheme: UserThemePreferenceModel) {
        val systemTheme = if (context.isDarkThemeOn()) {
            UserThemePreferenceModel.DARK
        } else {
            UserThemePreferenceModel.LIGHT
        }
        analytics.sendEvent(
            ThemeConfigurationEvent(
                systemTheme = systemTheme,
                selectedTheme = selectedTheme,
            )
        )
    }
}
