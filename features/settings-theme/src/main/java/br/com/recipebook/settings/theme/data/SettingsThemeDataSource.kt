package br.com.recipebook.settings.theme.data

import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel

interface SettingsThemeDataSource {
    fun getCurrentTheme(): UserThemePreferenceModel
    fun setCurrentTheme(theme: UserThemePreferenceModel)
}
