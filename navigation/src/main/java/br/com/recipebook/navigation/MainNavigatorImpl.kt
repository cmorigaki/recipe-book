package br.com.recipebook.navigation

import android.content.Context
import br.com.recipebook.navigation.intent.NavIntent

class MainNavigatorImpl(
    navigatorList: List<Navigator>
) : MainNavigator {

    private val intentMap: Map<Class<out NavIntent>, Navigator>

    init {
        intentMap = mutableMapOf()
        navigatorList.forEach { navigator ->
            navigator.intentList.forEach {
                intentMap[it] = navigator
            }
        }
    }

    override fun navigate(context: Context, intent: NavIntent) {
        intentMap[intent::class.java]?.navigate(context, intent)
    }
}