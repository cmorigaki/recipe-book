package br.com.recipebook.settings.theme.data

import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel
import br.com.recipebook.settings.theme.domain.repository.SettingsThemeRepository

class SettingsThemeRepositoryImpl(
    private val settingsThemeDataSource: SettingsThemeDataSource
) : SettingsThemeRepository {

    override fun getCurrentTheme(): UserThemePreferenceModel {
        return settingsThemeDataSource.getCurrentTheme()
    }

    override fun setCurrentTheme(theme: UserThemePreferenceModel) {
        settingsThemeDataSource.setCurrentTheme(theme)
    }
}