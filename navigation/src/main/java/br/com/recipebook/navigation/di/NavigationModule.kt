package br.com.recipebook.navigation.di

import br.com.recipebook.navigation.MainNavigator
import br.com.recipebook.navigation.MainNavigatorImpl
import org.koin.dsl.module

val navigationModule = module {
    single<MainNavigator> {
        MainNavigatorImpl(
            navigatorList = getAll()
        )
    }
}
