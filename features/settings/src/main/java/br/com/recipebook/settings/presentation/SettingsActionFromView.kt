package br.com.recipebook.settings.presentation

import br.com.recipebook.settings.presentation.model.SettingsItem

sealed class SettingsActionFromView {
    data class ItemClick(val settingsItem: SettingsItem) : SettingsActionFromView()
}
