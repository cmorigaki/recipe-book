package br.com.recipebook.settings.theme.presentation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
class SettingsThemeViewState {
    val isLoading = MutableStateFlow(true)

    val hasError = MutableStateFlow(false)

    val isSystemThemeSelected = MutableStateFlow(false)
    val isLightThemeSelected = MutableStateFlow(false)
    val isDarkThemeSelected = MutableStateFlow(false)
}
