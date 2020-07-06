package br.com.recipebook.recipecollection.presentation

sealed class RecipeCollectionActionToView {
    data class OpenRecipeDetail(val recipeId: String, val title: String?) :
        RecipeCollectionActionToView()
}
