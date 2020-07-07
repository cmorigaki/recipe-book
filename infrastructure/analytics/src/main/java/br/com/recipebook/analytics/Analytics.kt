package br.com.recipebook.analytics

interface Analytics {
    fun sendEvent(event: Event)
}
