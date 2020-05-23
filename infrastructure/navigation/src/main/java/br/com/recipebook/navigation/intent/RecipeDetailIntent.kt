package br.com.recipebook.navigation.intent

data class RecipeDetailIntent(
    val recipeId: String,
    val title: String?
) : NavIntent