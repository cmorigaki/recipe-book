package br.com.recipebook.recipecollection.presentation

sealed class RecipeCollectionActionFromView {
    object onDisplayScreen : RecipeCollectionActionFromView()
    object Refresh : RecipeCollectionActionFromView()
    data class RecipeClick(val recipeId: String, val title: String?) :
        RecipeCollectionActionFromView()
}