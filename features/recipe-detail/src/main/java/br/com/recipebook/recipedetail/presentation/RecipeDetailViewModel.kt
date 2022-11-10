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
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val safeArgs: RecipeDetailSafeArgs,
    private val getRecipeDetail: GetRecipeDetailUseCase,
    private val analytics: Analytics,
    override val viewState: MutableStateFlow<RecipeDetailViewState> =
        MutableStateFlow(RecipeDetailViewState.Loading(safeArgs.title)),
) : BaseViewModel<MutableStateFlow<RecipeDetailViewState>, RecipeDetailAction, RecipeDetailCommand>() {

    init {
        viewModelScope.launch {
            getRecipeDetail(safeArgs.recipeId).onSuccess(::onLoadSuccess).onFailure { onLoadError() }
        }
    }

    override fun dispatchAction(action: RecipeDetailAction) = Unit

    private fun onLoadSuccess(detail: RecipeDetailModel) {
        sendViewEvent(true)

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

        viewState.value = RecipeDetailViewState.Loaded(
            title = safeArgs.title,
            recipeImage = detail.imgPath,
            listItems = itemList,
        )
    }

    private fun onLoadError() {
        sendViewEvent(false)
        viewState.value = RecipeDetailViewState.Error(safeArgs.title)
    }

    private fun sendViewEvent(success: Boolean) {
        analytics.sendEvent(ViewRecipeDetailEvent(success))
    }
}
