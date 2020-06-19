package br.com.recipebook.analytics.events

import br.com.recipebook.analytics.Event

class ViewScreenEvent(
    val screenName: String
) : Event {
    override val id: String = "view_screen"
    override val properties: Map<String, Any?>
        get() = mapOf("screen_name" to screenName)
}