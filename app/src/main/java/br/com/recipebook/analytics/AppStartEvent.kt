package br.com.recipebook.analytics

enum class InitMode(val value: String) {
    COLD_START("cold_start"),
    LUKEWARM_START("lukewarm_start")
}

class AppStartEvent(
    private val startMode: InitMode
) : Event {
    override val id = "app_start"
    override val properties: Map<String, Any?>
        get() = mapOf(
            "start_mode" to startMode.value
        )
}
