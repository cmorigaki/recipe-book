package br.com.recipebook.settings.presentation.model

import androidx.annotation.StringRes
import br.com.recipebook.navigation.intent.NavIntent

data class SettingsItem(
    val id: String,
    @StringRes val title: Int,
    val navIntent: NavIntent
)