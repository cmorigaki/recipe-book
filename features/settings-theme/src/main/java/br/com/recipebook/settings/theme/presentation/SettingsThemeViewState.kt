package br.com.recipebook.settings.theme.presentation

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
sealed class SettingsThemeViewState {
    object Loading : SettingsThemeViewState()

    object Error : SettingsThemeViewState()
    data class Loaded(
        val isSystemThemeSelected: Boolean,
        val isLightThemeSelected: Boolean,
        val isDarkThemeSelected: Boolean,
    ) : SettingsThemeViewState()
}
