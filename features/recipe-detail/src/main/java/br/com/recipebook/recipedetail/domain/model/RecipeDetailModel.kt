package br.com.recipebook.recipedetail.domain.model

data class RecipeDetailModel(
    val id: String,
    val name: String,
    val imgPath: String?,
    val prepTimeMin: Int?,
    val cookTimeMin: Int?,
    val ingredients: List<IngredientsModel>,
    val instructions: List<String>
)

data class IngredientsModel(
    val description: String,
    val imgPath: String?
)
