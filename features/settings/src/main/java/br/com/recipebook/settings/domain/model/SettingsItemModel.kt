package br.com.recipebook.settings.domain.model

import androidx.annotation.StringRes
import br.com.recipebook.navigation.intent.NavIntent

data class SettingsItemModel(
    val id: String,
    @StringRes val title: Int,
    val navIntent: NavIntent
)
