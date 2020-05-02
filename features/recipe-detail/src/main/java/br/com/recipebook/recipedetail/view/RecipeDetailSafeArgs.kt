package br.com.recipebook.recipedetail.view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeDetailSafeArgs(
    val recipeId: String
) : Parcelable