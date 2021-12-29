package br.com.recipebook.settings.theme.di

import android.content.Context
import android.content.SharedPreferences
import br.com.recipebook.navigation.Navigator
import br.com.recipebook.settings.theme.data.SettingsThemeDataSource
import br.com.recipebook.settings.theme.data.SettingsThemeRepositoryImpl
import br.com.recipebook.settings.theme.data.local.NamesSharedPreferencesSettingsTheme
import br.com.recipebook.settings.theme.data.local.SettingsThemeLocalDataSource
import br.com.recipebook.settings.theme.domain.repository.SettingsThemeRepository
import br.com.recipebook.settings.theme.domain.usecase.ApplyUserThemePreference
import br.com.recipebook.settings.theme.domain.usecase.ApplyUserThemePreferenceUseCase
import br.com.recipebook.settings.theme.domain.usecase.GetUserThemePreference
import br.com.recipebook.settings.theme.domain.usecase.GetUserThemePreferenceUseCase
import br.com.recipebook.settings.theme.domain.usecase.SetUserThemePreference
import br.com.recipebook.settings.theme.domain.usecase.SetUserThemePreferenceUseCase
import br.com.recipebook.settings.theme.presentation.SettingsThemeViewModel
import br.com.recipebook.settings.theme.presentation.SettingsThemeViewState
import br.com.recipebook.settings.theme.view.SettingsThemeNavigator
import br.com.recipebook.startup.StartupJob
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val settingsThemeViewModule = module {
    factory {
        SettingsThemeNavigator()
    } bind Navigator::class
}

val settingsThemePresentationModule = module {
    viewModel {
        SettingsThemeViewModel(
            viewState = SettingsThemeViewState(),
            getUserThemePreference = get(),
            setUserThemePreferenceUseCase = get(),
            analytics = get()
        )
    }
}

val settingsThemeDomainModule = module {
    factory<GetUserThemePreferenceUseCase> {
        GetUserThemePreference(get())
    }
    factory<SetUserThemePreferenceUseCase> {
        SetUserThemePreference(get(), get())
    }
    factory<ApplyUserThemePreferenceUseCase> {
        ApplyUserThemePreference(get(), get(), get())
    } bind StartupJob::class
}

val settingsDataModule = module {
    factory<SettingsThemeRepository> {
        SettingsThemeRepositoryImpl(get())
    }

    factory<SettingsThemeDataSource> {
        SettingsThemeLocalDataSource(get(named(NamesSharedPreferencesSettingsTheme)))
    }

    factory<SharedPreferences>(named(NamesSharedPreferencesSettingsTheme)) {
        androidContext().getSharedPreferences(
            NamesSharedPreferencesSettingsTheme,
            Context.MODE_PRIVATE
        )
    }
}

val settingsThemeModules = listOf(
    settingsThemePresentationModule,
    settingsThemeViewModule,
    settingsThemeDomainModule,
    settingsDataModule
)
