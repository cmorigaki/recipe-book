package br.com.recipebook.monitoring.network.analytics

import br.com.recipebook.analytics.Event

data class NetworkCall(
    private val time: Long,
    private val method: String,
    private val host: String,
    private val path: String,
    private val query: String?,
    private val status: Int?,
    private val exception: String?,
) : Event {
    override val id = "network_call"
    override val properties: Map<String, Any?>
        get() = mapOf(
            "time" to time,
            "method" to method,
            "host" to host,
            "path" to path,
            "query" to query,
            "status" to status,
            "exception" to exception,
        )
}
