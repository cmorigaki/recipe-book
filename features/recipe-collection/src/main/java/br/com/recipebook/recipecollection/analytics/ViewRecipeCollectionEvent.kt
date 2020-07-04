package br.com.recipebook.recipecollection.analytics

import br.com.recipebook.analytics.Event

data class ViewRecipeCollectionEvent(
    private val loadSuccess: Boolean
) : Event {
    override val id: String = "view_recipe_collection"
    override val properties: Map<String, Any?>
        get() = mapOf(
            "load_success" to loadSuccess
        )
}