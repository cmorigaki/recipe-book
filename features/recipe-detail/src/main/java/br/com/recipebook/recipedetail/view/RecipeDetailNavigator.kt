package br.com.recipebook.recipedetail.view

import android.content.Context
import br.com.recipebook.navigation.Navigator
import br.com.recipebook.navigation.intent.NavIntent
import br.com.recipebook.navigation.intent.RecipeDetailIntent

class RecipeDetailNavigator : Navigator {
    override var intentList = listOf(RecipeDetailIntent::class.java)

    override fun navigate(context: Context, intent: NavIntent) {
        if (intent is RecipeDetailIntent) {
            context.startActivity(RecipeDetailActivity.newIntent(context, intent.recipeId))
        }
    }
}