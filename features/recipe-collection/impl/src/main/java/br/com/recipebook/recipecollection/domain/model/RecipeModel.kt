package br.com.recipebook.recipecollection.domain.model

import java.math.BigDecimal

data class RecipeModel(
    val imgPath: String?,
    val title: String?,
    val category: String?,
    val portionSize: BigDecimal?
)