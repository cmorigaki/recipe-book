package br.com.recipebook.recipedetail.analytics

import br.com.recipebook.analytics.Event

data class ViewRecipeDetailEvent(
    private val loadSuccess: Boolean
) : Event {
    override val id: String = "view_recipe_detail"
    override val properties: Map<String, Any?>
        get() = mapOf(
            "load_success" to loadSuccess
        )
}
