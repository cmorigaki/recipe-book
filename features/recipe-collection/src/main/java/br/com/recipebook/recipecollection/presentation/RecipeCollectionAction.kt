package br.com.recipebook.recipecollection.presentation

sealed class RecipeCollectionAction {
    object Refresh : RecipeCollectionAction()
    data class RecipeClick(
        val recipeId: String,
        val title: String?
    ) : RecipeCollectionAction()
}
