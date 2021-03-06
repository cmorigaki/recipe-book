package br.com.recipebook.recipedetail.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeDetailResponse(
    val id: String,
    val name: String,
    val imgPath: String?,
    val prepTimeMin: Int?,
    val cookTimeMin: Int?,
    val ingredients: List<IngredientsResponse>,
    val instructions: List<String>
)

@JsonClass(generateAdapter = true)
data class IngredientsResponse(
    val description: String,
    val imgPath: String?
)
