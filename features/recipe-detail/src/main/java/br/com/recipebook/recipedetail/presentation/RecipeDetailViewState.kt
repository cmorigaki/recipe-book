package br.com.recipebook.recipedetail.presentation

import br.com.recipebook.recipedetail.presentation.model.RecipeDetailItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

@ExperimentalCoroutinesApi
class RecipeDetailViewState {
    val isLoading = MutableStateFlow(true)

    val hasError = MutableStateFlow(false)

    val recipeImage = MutableStateFlow<String?>("")
    val title = MutableStateFlow<String?>(null)
    val listItems = MutableStateFlow<List<RecipeDetailItem>>(emptyList())
}
