package br.com.recipebook.device.di

import br.com.recipebook.device.ActivityProvider
import br.com.recipebook.device.LocaleProvider
import br.com.recipebook.device.LocaleProviderImpl
import org.koin.dsl.module

val deviceModules = module {
    single<LocaleProvider> { LocaleProviderImpl() }
    single { ActivityProvider(get()) }
}
