package br.com.recipebook.recipedetail.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeDetailSafeArgs(
    val recipeId: String,
    val title: String?
) : Parcelable
