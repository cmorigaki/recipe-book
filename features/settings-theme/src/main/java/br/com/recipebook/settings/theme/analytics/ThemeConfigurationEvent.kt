package br.com.recipebook.settings.theme.analytics

import br.com.recipebook.analytics.Event
import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel

data class ThemeConfigurationEvent(
    private val systemTheme: UserThemePreferenceModel,
    private val selectedTheme: UserThemePreferenceModel,
) : Event {
    override val id: String = "theme_configuration"
    override val properties: Map<String, Any?>
        get() = mapOf(
            "system_theme" to systemTheme.name,
            "selected_theme" to selectedTheme.name,
        )
}
