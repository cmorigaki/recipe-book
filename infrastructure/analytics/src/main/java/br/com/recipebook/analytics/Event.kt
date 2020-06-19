package br.com.recipebook.analytics

interface Event {
    val id: String
    val properties: Map<String, Any?>
}