package br.com.recipebook.settings.analytics

import br.com.recipebook.analytics.Event

data class ViewSettingsEvent(
    private val loadSuccess: Boolean
) : Event {
    override val id: String = "view_settings"
    override val properties: Map<String, Any?>
        get() = mapOf(
            "load_success" to loadSuccess
        )
}