package br.com.recipebook.recipecollection.presentation

sealed class RecipeCollectionCommand {
    data class OpenRecipeDetail(val recipeId: String, val title: String?) : RecipeCollectionCommand()
}
