package br.com.recipebook.settings.theme.presentation

import androidx.lifecycle.viewModelScope
import br.com.recipebook.analytics.Analytics
import br.com.recipebook.settings.theme.analytics.ViewSettingsThemeEvent
import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel
import br.com.recipebook.settings.theme.domain.usecase.GetUserThemePreferenceUseCase
import br.com.recipebook.settings.theme.domain.usecase.SetUserThemePreferenceUseCase
import br.com.recipebook.utilityandroid.presentation.BaseViewModel
import br.com.recipebook.utilitykotlin.CommonError
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SettingsThemeViewModel(
    override val viewState: SettingsThemeViewState,
    private val getUserThemePreference: GetUserThemePreferenceUseCase,
    private val setUserThemePreferenceUseCase: SetUserThemePreferenceUseCase,
    private val analytics: Analytics
) : BaseViewModel<SettingsThemeViewState, SettingsThemeAction, SettingsThemeCommand>() {

    init {
        viewModelScope.launch {
            setLoadingState()
            getUserThemePreference().onSuccess(::onLoadSuccess).onFailure(::onLoadError)
        }
    }

    override fun dispatchAction(action: SettingsThemeAction) {
        viewModelScope.launch {
            when (action) {
                is SettingsThemeAction.SystemThemeSelected ->
                    setUserThemePreferenceUseCase(UserThemePreferenceModel.SYSTEM)
                is SettingsThemeAction.LightThemeSelected ->
                    setUserThemePreferenceUseCase(UserThemePreferenceModel.LIGHT)
                is SettingsThemeAction.DarkThemeSelected ->
                    setUserThemePreferenceUseCase(UserThemePreferenceModel.DARK)
            }
        }
    }

    private fun setLoadingState() {
        viewState.isLoading.value = true
        viewState.hasError.value = false
    }

    private fun setErrorState() {
        viewState.isLoading.value = false
        viewState.hasError.value = true
    }

    private fun setSuccessState() {
        viewState.isLoading.value = false
        viewState.hasError.value = false
    }

    private fun onLoadSuccess(settings: UserThemePreferenceModel) {
        sendViewEvent(true)
        when (settings) {
            UserThemePreferenceModel.SYSTEM -> {
                viewState.isSystemThemeSelected.value = true
                viewState.isLightThemeSelected.value = false
                viewState.isDarkThemeSelected.value = false
            }
            UserThemePreferenceModel.LIGHT -> {
                viewState.isSystemThemeSelected.value = false
                viewState.isLightThemeSelected.value = true
                viewState.isDarkThemeSelected.value = false
            }
            UserThemePreferenceModel.DARK -> {
                viewState.isSystemThemeSelected.value = false
                viewState.isLightThemeSelected.value = false
                viewState.isDarkThemeSelected.value = true
            }
        }
        setSuccessState()
    }

    private fun onLoadError(error: CommonError) {
        sendViewEvent(false)
        setErrorState()
    }

    private fun sendViewEvent(success: Boolean) {
        analytics.sendEvent(ViewSettingsThemeEvent(success))
    }
}
