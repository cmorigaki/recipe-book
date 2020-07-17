package br.com.recipebook.recipecollection.presentation

import br.com.recipebook.recipecollection.view.RecipeItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
class RecipeCollectionViewState {
    val recipes = MutableStateFlow<List<RecipeItem>>(emptyList())
    val isLoading = MutableStateFlow(false)
    val hasError = MutableStateFlow(false)
}
