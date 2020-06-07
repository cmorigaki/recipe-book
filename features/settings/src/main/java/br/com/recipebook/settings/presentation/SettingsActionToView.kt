package br.com.recipebook.settings.presentation

import br.com.recipebook.navigation.intent.NavIntent

sealed class SettingsActionToView {
    data class OpenItem(val navIntent: NavIntent) : SettingsActionToView()
}