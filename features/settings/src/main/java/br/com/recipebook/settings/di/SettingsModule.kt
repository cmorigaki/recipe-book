package br.com.recipebook.settings.di

import br.com.recipebook.navigation.Navigator
import br.com.recipebook.settings.domain.usecase.GetSettings
import br.com.recipebook.settings.domain.usecase.GetSettingsUseCase
import br.com.recipebook.settings.presentation.SettingsViewModel
import br.com.recipebook.settings.view.SettingsNavigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val settingsViewModule = module {
    factory {
        SettingsNavigator()
    } bind Navigator::class
}

val settingsPresentationModule = module {
    viewModel {
        SettingsViewModel(
            getSettingsList = get(),
            analytics = get(),
            buildConfiguration = get(),
        )
    }
}

val settingsDomainModule = module {
    factory<GetSettingsUseCase> {
        GetSettings()
    }
}

val settingsModules = listOf(
    settingsPresentationModule,
    settingsViewModule,
    settingsDomainModule
)
