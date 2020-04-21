package br.com.recipebook.navigation

import android.content.Context
import br.com.recipebook.navigation.intent.NavIntent

interface Navigator {
    val intentList: List<Class<out NavIntent>>

    fun navigate(context: Context, intent: NavIntent)
}