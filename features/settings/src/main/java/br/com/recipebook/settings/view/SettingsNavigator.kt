package br.com.recipebook.settings.view

import android.content.Context
import br.com.recipebook.navigation.Navigator
import br.com.recipebook.navigation.intent.NavIntent
import br.com.recipebook.navigation.intent.SettingsIntent

class SettingsNavigator : Navigator {
    override var intentList = listOf(SettingsIntent::class.java)

    override fun navigate(context: Context, intent: NavIntent) {
        if (intent is SettingsIntent) {
            context.startActivity(
                SettingsActivity.newIntent(
                    context = context
                )
            )
        }
    }
}