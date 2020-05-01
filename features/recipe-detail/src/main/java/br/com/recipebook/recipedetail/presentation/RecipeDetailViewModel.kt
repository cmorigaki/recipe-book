package br.com.recipebook.recipedetail.presentation

import androidx.lifecycle.viewModelScope
import br.com.recipebook.recipedetail.domain.model.RecipeDetailModel
import br.com.recipebook.recipedetail.domain.usecase.GetRecipeDetailUseCase
import br.com.recipebook.recipedetail.presentation.model.DescriptionItem
import br.com.recipebook.recipedetail.presentation.model.IngredientHeaderItem
import br.com.recipebook.recipedetail.presentation.model.InstructionHeaderItem
import br.com.recipebook.recipedetail.presentation.model.RecipeDetailItem
import br.com.recipebook.utilityandroid.presentation.BaseViewModel
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    override val viewState: RecipeDetailViewState,
    private val getRecipeDetail: GetRecipeDetailUseCase
) : BaseViewModel<RecipeDetailViewState, RecipeDetailViewAction>() {

    init {
        viewModelScope.launch {
            getRecipeDetail("bolo_de_cenoura").mapSuccess(::onLoadSuccess)
        }
    }

    override fun dispatchViewAction(action: RecipeDetailViewAction) {

    }

    private fun onLoadSuccess(detail: RecipeDetailModel) {
        viewState.title.value = detail.name
        viewState.recipeImage.value = detail.imgPath

        val itemList = mutableListOf<RecipeDetailItem>(IngredientHeaderItem)
        itemList.addAll(detail.ingredients.map {
            DescriptionItem(
                description = it.description,
                imgPath = it.imgPath
            )
        })
        itemList.add(InstructionHeaderItem)
        itemList.addAll(detail.instructions.map { DescriptionItem(description = it) })

        viewState.listItems.value = itemList
    }
}