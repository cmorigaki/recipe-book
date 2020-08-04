package br.com.recipebook.settings.theme.presentation

sealed class SettingsThemeAction {
    object SystemThemeSelected : SettingsThemeAction()
    object LightThemeSelected : SettingsThemeAction()
    object DarkThemeSelected : SettingsThemeAction()
}
