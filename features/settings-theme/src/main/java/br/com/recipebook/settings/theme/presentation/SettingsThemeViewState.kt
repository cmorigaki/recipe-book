package br.com.recipebook.settings.theme.presentation

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
sealed class SettingsThemeViewState {
    object Loading : SettingsThemeViewState()
    object Error : SettingsThemeViewState()
    data class Loaded(val selectedTheme: SettingsThemeAction) : SettingsThemeViewState()
}
