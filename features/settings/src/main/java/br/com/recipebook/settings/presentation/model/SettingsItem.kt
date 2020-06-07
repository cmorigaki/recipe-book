package br.com.recipebook.settings.presentation.model

import br.com.recipebook.navigation.intent.NavIntent

data class SettingsItem(
    val id: String,
    val title: String,
    val navIntent: NavIntent
)