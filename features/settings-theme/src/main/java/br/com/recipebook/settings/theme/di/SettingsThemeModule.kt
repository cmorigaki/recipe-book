package br.com.recipebook.settings.theme.di

import android.content.Context
import android.content.SharedPreferences
import br.com.recipebook.navigation.Navigator
import br.com.recipebook.settings.theme.data.SettingsThemeDataSource
import br.com.recipebook.settings.theme.data.SettingsThemeRepositoryImpl
import br.com.recipebook.settings.theme.data.local.NAMED_SHARED_PREFERENCES_SETTINGS_THEME
import br.com.recipebook.settings.theme.data.local.SettingsThemeLocalDataSource
import br.com.recipebook.settings.theme.domain.repository.SettingsThemeRepository
import br.com.recipebook.settings.theme.domain.usecase.GetUserThemePreference
import br.com.recipebook.settings.theme.domain.usecase.GetUserThemePreferenceUseCase
import br.com.recipebook.settings.theme.domain.usecase.SetUserThemePreference
import br.com.recipebook.settings.theme.domain.usecase.SetUserThemePreferenceUseCase
import br.com.recipebook.settings.theme.presentation.SettingsThemeViewModel
import br.com.recipebook.settings.theme.presentation.SettingsThemeViewState
import br.com.recipebook.settings.theme.view.SettingsThemeNavigator
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val settingsThemeViewModule = module {
    single {
        SettingsThemeNavigator()
    } bind Navigator::class
}

val settingsThemePresentationModule = module {
    viewModel {
        SettingsThemeViewModel(
            viewState = SettingsThemeViewState(),
            getUserThemePreference = get(),
            setUserThemePreferenceUseCase = get()
        )
    }
}

val settingsThemeDomainModule = module {
    single<GetUserThemePreferenceUseCase> {
        GetUserThemePreference(get())
    }
    single<SetUserThemePreferenceUseCase> {
        SetUserThemePreference(get())
    }
}

val settingsDataModule = module {
    single<SettingsThemeRepository> {
        SettingsThemeRepositoryImpl(get())
    }

    single<SettingsThemeDataSource> {
        SettingsThemeLocalDataSource(get(named(NAMED_SHARED_PREFERENCES_SETTINGS_THEME)))
    }

    single<SharedPreferences>(named(NAMED_SHARED_PREFERENCES_SETTINGS_THEME)) {
        androidContext().getSharedPreferences(
            NAMED_SHARED_PREFERENCES_SETTINGS_THEME,
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