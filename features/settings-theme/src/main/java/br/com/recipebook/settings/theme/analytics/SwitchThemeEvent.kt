package br.com.recipebook.settings.theme.analytics

import br.com.recipebook.analytics.Event
import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel

data class SwitchThemeEvent(
    private val theme: UserThemePreferenceModel
) : Event {
    override val id: String = "switch_theme"
    override val properties: Map<String, Any?>
        get() = mapOf(
            "theme" to theme.name
        )
}
