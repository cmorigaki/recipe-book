package br.com.recipebook.recipecollection.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.recipebook.recipecollection.di.ServiceLocator
import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.recipecollection.view.RecipeItem
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper
import kotlinx.coroutines.launch

class RecipeCollectionViewModel : ViewModel() {

    val viewState = RecipeCollectionViewState()
    private val recipeCollectionRepository = ServiceLocator.getRecipeCollectionRepository()

    init {
        setInitialState()
        loadRecipeList()
    }

    private fun setInitialState() {
        viewState.isLoading.value = true
        viewState.recipes.value = emptyList()
        viewState.hasError.value = false
    }

    private fun loadRecipeList() = viewModelScope.launch {
        viewState.isLoading.value = true

        when (val result = recipeCollectionRepository.getRecipeCollection()) {
            is ResultWrapper.Success -> onLoadRecipeListSuccess(result.data)
            is ResultWrapper.Failure -> onLoadRecipeListError(result.error)
        }

        viewState.isLoading.value = false
    }

    private fun onLoadRecipeListSuccess(list: List<RecipeModel>) {
        viewState.hasError.value = false
        viewState.recipes.value = list.map {
            RecipeItem(it.imgPath, it.title, it.category, it.portionSize)
        }
    }

    private fun onLoadRecipeListError(error: CommonError) {
        viewState.hasError.value = true
    }
}