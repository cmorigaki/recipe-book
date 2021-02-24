package br.com.recipebook.inappupdate.analytics

import br.com.recipebook.analytics.Event

data class InAppUpdateEvent(
    private val shouldUpdate: Boolean,
    private val updateStatus: String?,
    private val currentVersionCode: Int,
) : Event {
    override val id: String = "in_app_update"
    override val properties: Map<String, Any?>
        get() = mapOf(
            "should_update" to shouldUpdate,
            "update_status" to updateStatus,
            "current_version_code" to currentVersionCode,
        )
}
