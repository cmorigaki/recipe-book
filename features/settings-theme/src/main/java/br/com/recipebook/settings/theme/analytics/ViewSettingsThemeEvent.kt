package br.com.recipebook.settings.theme.analytics

import br.com.recipebook.analytics.Event

data class ViewSettingsThemeEvent(
    private val loadSuccess: Boolean
) : Event {
    override val id: String = "view_settings_theme"
    override val properties: Map<String, Any?>
        get() = mapOf(
            "load_success" to loadSuccess
        )
}
