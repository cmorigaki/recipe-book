package br.com.recipebook.recipedetail.presentation

import androidx.lifecycle.MutableLiveData
import br.com.recipebook.recipedetail.domain.model.IngredientsModel
import br.com.recipebook.recipedetail.presentation.model.RecipeDetailItem

class RecipeDetailViewState {
    val isLoading = MutableLiveData<Boolean>()

    val hasError = MutableLiveData<Boolean>()

    val recipeImage = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val listItems = MutableLiveData<List<RecipeDetailItem>>()
}