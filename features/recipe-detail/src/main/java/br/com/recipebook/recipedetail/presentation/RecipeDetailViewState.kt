package br.com.recipebook.recipedetail.presentation

import androidx.lifecycle.MutableLiveData

class RecipeDetailViewState {
    val recipeImage = MutableLiveData<String>()
    val title = MutableLiveData<String>()
}