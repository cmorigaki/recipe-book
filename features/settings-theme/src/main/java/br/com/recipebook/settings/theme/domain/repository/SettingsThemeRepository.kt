package br.com.recipebook.settings.theme.domain.repository

import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel

interface SettingsThemeRepository {
    fun getCurrentTheme(): UserThemePreferenceModel
    fun setCurrentTheme(theme: UserThemePreferenceModel)
}