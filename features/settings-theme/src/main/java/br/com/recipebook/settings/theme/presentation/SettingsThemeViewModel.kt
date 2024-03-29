package br.com.recipebook.settings.theme.presentation

import androidx.lifecycle.viewModelScope
import br.com.recipebook.analytics.Analytics
import br.com.recipebook.settings.theme.analytics.ViewSettingsThemeEvent
import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel
import br.com.recipebook.settings.theme.domain.usecase.GetUserThemePreferenceUseCase
import br.com.recipebook.settings.theme.domain.usecase.SetUserThemePreferenceUseCase
import br.com.recipebook.utilityandroid.presentation.BaseViewModel
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SettingsThemeViewModel(
    private val getUserThemePreference: GetUserThemePreferenceUseCase,
    private val setUserThemePreferenceUseCase: SetUserThemePreferenceUseCase,
    private val analytics: Analytics,
    override val viewState: MutableStateFlow<SettingsThemeViewState> = MutableStateFlow(SettingsThemeViewState.Loading),
) : BaseViewModel<MutableStateFlow<SettingsThemeViewState>, SettingsThemeAction, SettingsThemeCommand>() {

    init {
        viewModelScope.launch {
            getUserThemePreference().onSuccess(::onLoadSuccess).onFailure { onLoadError() }
        }
    }

    override fun dispatchAction(action: SettingsThemeAction) {
        viewModelScope.launch {
            when (action) {
                is SettingsThemeAction.SystemThemeSelected -> {
                    viewState.value = SettingsThemeViewState.Loaded(
                        selectedTheme = SettingsThemeAction.SystemThemeSelected
                    )
                    setUserThemePreferenceUseCase(UserThemePreferenceModel.SYSTEM)
                }
                is SettingsThemeAction.LightThemeSelected -> {
                    viewState.value = SettingsThemeViewState.Loaded(
                        selectedTheme = SettingsThemeAction.LightThemeSelected
                    )
                    setUserThemePreferenceUseCase(UserThemePreferenceModel.LIGHT)
                }
                is SettingsThemeAction.DarkThemeSelected -> {
                    viewState.value = SettingsThemeViewState.Loaded(
                        selectedTheme = SettingsThemeAction.DarkThemeSelected
                    )
                    setUserThemePreferenceUseCase(UserThemePreferenceModel.DARK)
                }
            }
        }
    }

    private fun onLoadSuccess(settings: UserThemePreferenceModel) {
        sendViewEvent(true)
        viewState.value = when (settings) {
            UserThemePreferenceModel.SYSTEM -> {
                SettingsThemeViewState.Loaded(selectedTheme = SettingsThemeAction.SystemThemeSelected)
            }
            UserThemePreferenceModel.LIGHT -> {
                SettingsThemeViewState.Loaded(selectedTheme = SettingsThemeAction.LightThemeSelected)
            }
            UserThemePreferenceModel.DARK -> {
                SettingsThemeViewState.Loaded(selectedTheme = SettingsThemeAction.DarkThemeSelected)
            }
        }
    }

    private fun onLoadError() {
        sendViewEvent(false)
        viewState.value = SettingsThemeViewState.Error
    }

    private fun sendViewEvent(success: Boolean) {
        analytics.sendEvent(ViewSettingsThemeEvent(success))
    }
}
