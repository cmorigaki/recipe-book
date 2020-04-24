package br.com.recipebook.recipedetail.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeDetailResponse(
    val id: String,
    val name: String,
    val imgPath: String?
)