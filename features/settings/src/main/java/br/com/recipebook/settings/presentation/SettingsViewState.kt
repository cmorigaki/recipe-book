package br.com.recipebook.settings.presentation

import br.com.recipebook.settings.presentation.model.SettingsItem

sealed class SettingsViewState {
    abstract val appVersion: String

    data class Loading(override val appVersion: String) : SettingsViewState()

    data class Error(override val appVersion: String) : SettingsViewState()
    data class Loaded(
        val listItems: List<SettingsItem>,
        override val appVersion: String,
    ) : SettingsViewState()
}
