package br.com.recipebook.recipecollection.presentation

import androidx.lifecycle.MutableLiveData
import br.com.recipebook.recipecollection.view.RecipeItem

class RecipeCollectionViewState {
    val recipes = MutableLiveData<List<RecipeItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val hasError = MutableLiveData<Boolean>()
}