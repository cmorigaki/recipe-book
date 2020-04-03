package br.com.recipebook.recipecollection.view

import java.math.BigDecimal

data class RecipeItem(
    val imgUrl: String?,
    val title: String?,
    val category: String?,
    val portionSize: BigDecimal?
)