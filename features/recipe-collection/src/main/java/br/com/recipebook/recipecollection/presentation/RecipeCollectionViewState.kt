package br.com.recipebook.recipecollection.presentation

import br.com.recipebook.recipecollection.view.RecipeItem
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
sealed class RecipeCollectionViewState {
    object Loading : RecipeCollectionViewState()
    object Error : RecipeCollectionViewState()
    data class Loaded(
        val recipes: List<RecipeItem>,
        val isRefreshing: Boolean,
    ) : RecipeCollectionViewState()
}
