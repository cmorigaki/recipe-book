package br.com.recipebook.recipedetail.presentation.model

sealed class RecipeDetailItem

object IngredientHeaderItem : RecipeDetailItem()

data class DescriptionItem(
    val description: String,
    val imgPath: String? = null
) : RecipeDetailItem()

object InstructionHeaderItem : RecipeDetailItem()