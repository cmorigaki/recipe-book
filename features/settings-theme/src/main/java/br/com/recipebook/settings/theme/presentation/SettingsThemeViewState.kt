package br.com.recipebook.settings.theme.presentation

import androidx.lifecycle.MutableLiveData

class SettingsThemeViewState {
    val isLoading = MutableLiveData<Boolean>()

    val hasError = MutableLiveData<Boolean>()

    val isSystemThemeSelected = MutableLiveData<Boolean>()
    val isLightThemeSelected = MutableLiveData<Boolean>()
    val isDarkThemeSelected = MutableLiveData<Boolean>()
}