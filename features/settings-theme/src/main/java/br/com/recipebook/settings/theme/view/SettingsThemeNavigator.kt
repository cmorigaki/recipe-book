package br.com.recipebook.settings.theme.view

import android.content.Context
import br.com.recipebook.navigation.Navigator
import br.com.recipebook.navigation.intent.NavIntent
import br.com.recipebook.navigation.intent.SettingsThemeIntent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SettingsThemeNavigator : Navigator {
    override var intentList = listOf(SettingsThemeIntent::class.java)

    override fun navigate(context: Context, intent: NavIntent) {
        if (intent is SettingsThemeIntent) {
            context.startActivity(
                SettingsThemeActivity.newIntent(
                    context = context
                )
            )
        }
    }
}
