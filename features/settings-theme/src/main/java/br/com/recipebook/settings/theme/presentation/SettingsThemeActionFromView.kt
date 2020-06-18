package br.com.recipebook.settings.theme.presentation

sealed class SettingsThemeActionFromView {
    object SystemThemeSelected : SettingsThemeActionFromView()
    object LightThemeSelected : SettingsThemeActionFromView()
    object DarkThemeSelected : SettingsThemeActionFromView()
}