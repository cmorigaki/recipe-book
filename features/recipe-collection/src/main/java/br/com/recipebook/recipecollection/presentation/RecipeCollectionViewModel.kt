package br.com.recipebook.recipecollection.presentation

import androidx.lifecycle.viewModelScope
import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.recipecollection.domain.usecase.GetRecipeCollectionUseCase
import br.com.recipebook.recipecollection.view.RecipeItem
import br.com.recipebook.utilityandroid.presentation.BaseViewModel
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper
import kotlinx.coroutines.launch

class RecipeCollectionViewModel(
    override val viewState: RecipeCollectionViewState,
    private val getRecipeCollection: GetRecipeCollectionUseCase
) : BaseViewModel<RecipeCollectionViewState, RecipeCollectionViewAction>() {

    init {
        setInitialState()
        loadRecipeList()
    }

    override fun dispatchViewAction(action: RecipeCollectionViewAction) {
        when (action) {
            is RecipeCollectionViewAction.Refresh -> loadRecipeList()
        }
    }

    private fun setInitialState() {
        viewState.isLoading.value = true
        viewState.recipes.value = emptyList()
        viewState.hasError.value = false
    }

    private fun loadRecipeList() = viewModelScope.launch {
        viewState.isLoading.value = true

        when (val result = getRecipeCollection()) {
            is ResultWrapper.Success -> onLoadRecipeListSuccess(result.data)
            is ResultWrapper.Failure -> onLoadRecipeListError(result.error)
        }

        viewState.isLoading.value = false
    }

    private fun onLoadRecipeListSuccess(list: List<RecipeModel>) {
        viewState.hasError.value = false
        viewState.recipes.value = list.map {
            RecipeItem(
                id = it.id,
                imgPath = it.imgPath,
                title = it.title
            )
        }
    }

    private fun onLoadRecipeListError(error: CommonError) {
        viewState.hasError.value = true
    }
}