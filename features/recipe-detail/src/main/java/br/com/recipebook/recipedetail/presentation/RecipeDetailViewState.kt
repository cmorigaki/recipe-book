package br.com.recipebook.recipedetail.presentation

import androidx.lifecycle.MutableLiveData
import br.com.recipebook.recipedetail.domain.model.IngredientsModel
import br.com.recipebook.recipedetail.presentation.model.RecipeDetailItem

class RecipeDetailViewState {
    val recipeImage = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val prepTimeMin = MutableLiveData<Int?>()
    val cookTimeMin = MutableLiveData<Int?>()
    val listItems = MutableLiveData<List<RecipeDetailItem>>()
}