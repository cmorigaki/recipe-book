package br.com.recipebook.settings.presentation

import br.com.recipebook.navigation.intent.NavIntent

sealed class SettingsCommand {
    data class OpenItem(val navIntent: NavIntent) : SettingsCommand()
}
