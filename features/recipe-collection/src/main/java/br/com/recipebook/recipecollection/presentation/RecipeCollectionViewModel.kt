package br.com.recipebook.recipecollection.presentation

import androidx.lifecycle.viewModelScope
import br.com.recipebook.analytics.Analytics
import br.com.recipebook.recipecollection.analytics.ViewRecipeCollectionEvent
import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.recipecollection.domain.usecase.GetRecipeCollectionUseCase
import br.com.recipebook.recipecollection.view.RecipeItem
import br.com.recipebook.utilityandroid.presentation.BaseViewModel
import br.com.recipebook.utilitykotlin.CommonError
import br.com.recipebook.utilitykotlin.ResultWrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class RecipeCollectionViewModel(
    override val viewState: RecipeCollectionViewState,
    private val getRecipeCollection: GetRecipeCollectionUseCase,
    private val analytics: Analytics
) : BaseViewModel<RecipeCollectionViewState, RecipeCollectionAction, RecipeCollectionCommand>() {

    init {
        setInitialState()
        loadRecipeList()
    }

    override fun dispatchAction(action: RecipeCollectionAction) {
        when (action) {
            is RecipeCollectionAction.Refresh -> loadRecipeList()
            is RecipeCollectionAction.RecipeClick -> openRecipeDetail(
                recipeId = action.recipeId,
                title = action.title
            )
        }
    }

    private fun setInitialState() {
        viewState.isLoading.value = true
        viewState.recipes.value = emptyList()
        viewState.hasError.value = false
    }

    private fun loadRecipeList() = viewModelScope.launch {
        viewState.isLoading.value = true
        viewState.hasError.value = false

        when (val result = getRecipeCollection()) {
            is ResultWrapper.Success -> onLoadRecipeListSuccess(result.data)
            is ResultWrapper.Failure -> onLoadRecipeListError(result.error)
        }

        viewState.isLoading.value = false
    }

    private fun onLoadRecipeListSuccess(list: List<RecipeModel>) {
        sendViewEvent(true)
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
        sendViewEvent(false)
        viewState.hasError.value = true
        viewState.recipes.value = emptyList()
    }

    private fun openRecipeDetail(
        recipeId: String,
        title: String?
    ) = viewModelScope.launch {
        commandSender.send(
            RecipeCollectionCommand.OpenRecipeDetail(
                recipeId = recipeId, title = title
            )
        )
    }

    private fun sendViewEvent(success: Boolean) {
        analytics.sendEvent(ViewRecipeCollectionEvent(success))
    }
}
