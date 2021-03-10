package br.com.recipebook.analytics.events

import br.com.recipebook.analytics.Event

data class LibraryInitializationEvent(
    val libraryName: String,
    val timeToInitialize: Long?
) : Event {
    override val id: String = "library_initialization"
    override val properties: Map<String, Any?>
        get() = mapOf(
            "library_name" to libraryName,
            "time_to_initialize" to timeToInitialize
        )
}
