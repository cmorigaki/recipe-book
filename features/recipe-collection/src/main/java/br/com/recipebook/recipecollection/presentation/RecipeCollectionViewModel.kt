package br.com.recipebook.recipecollection.presentation

import androidx.lifecycle.viewModelScope
import br.com.recipebook.analytics.Analytics
import br.com.recipebook.inappupdate.domain.CheckInAppUpdateUseCase
import br.com.recipebook.recipecollection.analytics.ViewRecipeCollectionEvent
import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.recipecollection.domain.usecase.GetRecipeCollectionUseCase
import br.com.recipebook.recipecollection.view.RecipeItem
import br.com.recipebook.utilityandroid.presentation.BaseViewModel
import br.com.recipebook.utilitykotlin.CommonError
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class RecipeCollectionViewModel(
    override val viewState: RecipeCollectionViewState,
    private val getRecipeCollection: GetRecipeCollectionUseCase,
    private val analytics: Analytics,
    private val checkInAppUpdate: CheckInAppUpdateUseCase,
) : BaseViewModel<RecipeCollectionViewState, RecipeCollectionAction, RecipeCollectionCommand>() {

    init {
        setInitialState()
        viewModelScope.launch {
            if (checkInAppUpdate()) {
                loadRecipeList()
            } else {
                commandSender.send(RecipeCollectionCommand.FinishApp)
            }
        }
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

        getRecipeCollection()
            .onSuccess { onLoadRecipeListSuccess(it) }
            .onFailure { onLoadRecipeListError(it) }

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
