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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
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

    private fun loadRecipeList() = viewModelScope.launch {
        viewState.value = RecipeCollectionViewState.Loading

        getRecipeCollection()
            .onSuccess { onLoadRecipeListSuccess(it) }
            .onFailure { onLoadRecipeListError() }
    }

    private fun onLoadRecipeListSuccess(list: List<RecipeModel>) {
        sendViewEvent(true)
        viewState.value = RecipeCollectionViewState.Loaded(
            list.map {
                RecipeItem(
                    id = it.id,
                    imgPath = it.imgPath,
                    title = it.title
                )
            }
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
