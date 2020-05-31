package br.com.recipebook.recipecollection.presentation

sealed class RecipeCollectionActionFromView {
    object Refresh: RecipeCollectionActionFromView()
}