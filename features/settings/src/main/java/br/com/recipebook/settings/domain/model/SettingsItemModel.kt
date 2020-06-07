package br.com.recipebook.settings.domain.model

import br.com.recipebook.navigation.intent.NavIntent

data class SettingsItemModel (
    val id: String,
    val title: String,
    val navIntent: NavIntent
)