package br.com.recipebook.navigation

import android.content.Context
import br.com.recipebook.navigation.intent.NavIntent

interface MainNavigator {
    fun navigate(context: Context, intent: NavIntent)
}