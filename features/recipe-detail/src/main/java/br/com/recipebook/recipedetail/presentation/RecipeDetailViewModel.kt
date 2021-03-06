package br.com.recipebook.recipedetail.presentation

import androidx.lifecycle.viewModelScope
import br.com.recipebook.analytics.Analytics
import br.com.recipebook.recipedetail.analytics.ViewRecipeDetailEvent
import br.com.recipebook.recipedetail.domain.model.RecipeDetailModel
import br.com.recipebook.recipedetail.domain.usecase.GetRecipeDetailUseCase
import br.com.recipebook.recipedetail.presentation.model.DescriptionItem
import br.com.recipebook.recipedetail.presentation.model.IngredientHeaderItem
import br.com.recipebook.recipedetail.presentation.model.InstructionHeaderItem
import br.com.recipebook.recipedetail.presentation.model.RecipeDetailItem
import br.com.recipebook.recipedetail.view.RecipeDetailSafeArgs
import br.com.recipebook.utilityandroid.presentation.BaseViewModel
import br.com.recipebook.utilitykotlin.CommonError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class RecipeDetailViewModel(
    private val safeArgs: RecipeDetailSafeArgs,
    override val viewState: RecipeDetailViewState,
    private val getRecipeDetail: GetRecipeDetailUseCase,
    private val analytics: Analytics
) : BaseViewModel<RecipeDetailViewState, RecipeDetailAction, RecipeDetailCommand>() {

    init {
        viewModelScope.launch {
            viewState.title.value = safeArgs.title
            setLoadingState()
            getRecipeDetail(safeArgs.recipeId).mapSuccess(::onLoadSuccess).mapError(::onLoadError)
        }
    }

    override fun dispatchAction(action: RecipeDetailAction) {
    }

    private fun setLoadingState() {
        viewState.isLoading.value = true
        viewState.hasError.value = false
    }

    private fun setErrorState() {
        viewState.isLoading.value = false
        viewState.hasError.value = true
    }

    private fun setSuccessState() {
        viewState.isLoading.value = false
        viewState.hasError.value = false
    }

    private fun onLoadSuccess(detail: RecipeDetailModel) {
        sendViewEvent(true)
        viewState.title.value = detail.name
        viewState.recipeImage.value = detail.imgPath

        val itemList = mutableListOf<RecipeDetailItem>(IngredientHeaderItem)
        itemList.addAll(
            detail.ingredients.map {
                DescriptionItem(
                    description = it.description,
                    imgPath = it.imgPath
                )
            }
        )
        itemList.add(InstructionHeaderItem)
        itemList.addAll(detail.instructions.map { DescriptionItem(description = it) })

        viewState.listItems.value = itemList
        setSuccessState()
    }

    private fun onLoadError(error: CommonError) {
        sendViewEvent(false)
        setErrorState()
    }

    private fun sendViewEvent(success: Boolean) {
        analytics.sendEvent(ViewRecipeDetailEvent(success))
    }
}
