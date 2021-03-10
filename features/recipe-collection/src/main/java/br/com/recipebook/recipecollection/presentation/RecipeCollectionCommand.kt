package br.com.recipebook.recipecollection.presentation

sealed class RecipeCollectionCommand {
    object FinishApp : RecipeCollectionCommand()
    data class OpenRecipeDetail(val recipeId: String, val title: String?) : RecipeCollectionCommand()
}
