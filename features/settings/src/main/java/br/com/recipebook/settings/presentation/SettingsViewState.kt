package br.com.recipebook.settings.presentation

import br.com.recipebook.settings.presentation.model.SettingsItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
class SettingsViewState {
    val isLoading = MutableStateFlow(true)

    val hasError = MutableStateFlow(false)

    val listItems = MutableStateFlow<List<SettingsItem>>(emptyList())
    val appVersion = MutableStateFlow("")
}
