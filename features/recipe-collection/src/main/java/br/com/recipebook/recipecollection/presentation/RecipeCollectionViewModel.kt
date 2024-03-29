package br.com.recipebook.recipecollection.presentation

import androidx.lifecycle.viewModelScope
import br.com.recipebook.analytics.Analytics
import br.com.recipebook.inappupdate.domain.CheckInAppUpdateUseCase
import br.com.recipebook.recipecollection.analytics.ViewRecipeCollectionEvent
import br.com.recipebook.recipecollection.domain.model.RecipeModel
import br.com.recipebook.recipecollection.domain.usecase.GetRecipeCollectionUseCase
import br.com.recipebook.recipecollection.view.RecipeItem
import br.com.recipebook.utilityandroid.presentation.BaseViewModel
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RecipeCollectionViewModel(
    private val getRecipeCollection: GetRecipeCollectionUseCase,
    private val analytics: Analytics,
    private val checkInAppUpdate: CheckInAppUpdateUseCase,
    override val viewState: MutableStateFlow<RecipeCollectionViewState> =
        MutableStateFlow(RecipeCollectionViewState.Loading),
) : BaseViewModel<MutableStateFlow<RecipeCollectionViewState>, RecipeCollectionAction, RecipeCollectionCommand>() {

    init {
        viewModelScope.launch {
            if (checkInAppUpdate()) {
                loadRecipeList()
            } else {
                commandSender.emit(RecipeCollectionCommand.FinishApp)
            }
        }
    }

    override fun dispatchAction(action: RecipeCollectionAction) {
        when (action) {
            is RecipeCollectionAction.Refresh -> onRefresh()
            is RecipeCollectionAction.RecipeClick -> openRecipeDetail(
                recipeId = action.recipeId,
                title = action.title
            )
        }
    }

    private fun onRefresh() {
        (viewState.value as? RecipeCollectionViewState.Loaded)?.let {
            viewState.value = it.copy(isRefreshing = true)
            loadRecipeList()
        }
    }

    private fun loadRecipeList() = viewModelScope.launch {
        getRecipeCollection()
            .onSuccess { onLoadRecipeListSuccess(it) }
            .onFailure { onLoadRecipeListError() }
    }

    private fun onLoadRecipeListSuccess(list: List<RecipeModel>) {
        sendViewEvent(true)
        viewState.value = RecipeCollectionViewState.Loaded(
            recipes = list.map {
                RecipeItem(
                    id = it.id,
                    imgPath = it.imgPath,
                    title = it.title
                )
            },
            isRefreshing = false,
        )
    }

    private fun onLoadRecipeListError() {
        sendViewEvent(false)
        viewState.value = RecipeCollectionViewState.Error
    }

    private fun openRecipeDetail(
        recipeId: String,
        title: String?
    ) = viewModelScope.launch {
        commandSender.emit(
            RecipeCollectionCommand.OpenRecipeDetail(
                recipeId = recipeId, title = title
            )
        )
    }

    private fun sendViewEvent(success: Boolean) {
        analytics.sendEvent(ViewRecipeCollectionEvent(success))
    }
}
