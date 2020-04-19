package br.com.recipebook.recipecollection.data.remote

import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class RecipeListResponse(
    val recipeList: List<RecipeResponse>
)

@JsonClass(generateAdapter = true)
data class RecipeResponse(
    val title: String,
    val imgPath: String?
)