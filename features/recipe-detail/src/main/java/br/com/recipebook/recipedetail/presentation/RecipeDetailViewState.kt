package br.com.recipebook.recipedetail.presentation

import br.com.recipebook.recipedetail.presentation.model.RecipeDetailItem

sealed class RecipeDetailViewState {
    abstract val title: String?

    data class Loading(
        override val title: String?,
    ) : RecipeDetailViewState()

    data class Error(
        override val title: String?,
    ) : RecipeDetailViewState()

    data class Loaded(
        override val title: String?,
        val recipeImage: String?,
        val listItems: List<RecipeDetailItem>
    ) : RecipeDetailViewState()
}
