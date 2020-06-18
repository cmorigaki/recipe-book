package br.com.recipebook.settings.theme.presentation

import androidx.lifecycle.viewModelScope
import br.com.recipebook.settings.theme.domain.model.UserThemePreferenceModel
import br.com.recipebook.settings.theme.domain.usecase.GetUserThemePreferenceUseCase
import br.com.recipebook.settings.theme.domain.usecase.SetUserThemePreferenceUseCase
import br.com.recipebook.utilityandroid.presentation.BaseViewModel
import br.com.recipebook.utilitykotlin.CommonError
import kotlinx.coroutines.launch

class SettingsThemeViewModel(
    override val viewState: SettingsThemeViewState,
    private val getUserThemePreference: GetUserThemePreferenceUseCase,
    private val setUserThemePreferenceUseCase: SetUserThemePreferenceUseCase
) : BaseViewModel<SettingsThemeViewState, SettingsThemeActionFromView, SettingsThemeActionToView>() {

    init {
        viewModelScope.launch {
            setLoadingState()
            getUserThemePreference().mapSuccess(::onLoadSuccess).mapError(::setErrorState)
        }
    }

    override fun dispatchAction(action: SettingsThemeActionFromView) {
        viewModelScope.launch {
            when (action) {
                is SettingsThemeActionFromView.SystemThemeSelected ->
                    setUserThemePreferenceUseCase(UserThemePreferenceModel.SYSTEM)
                is SettingsThemeActionFromView.LightThemeSelected ->
                    setUserThemePreferenceUseCase(UserThemePreferenceModel.LIGHT)
                is SettingsThemeActionFromView.DarkThemeSelected ->
                    setUserThemePreferenceUseCase(UserThemePreferenceModel.DARK)
            }
        }
    }

    private fun setLoadingState() {
        viewState.isLoading.value = true
        viewState.hasError.value = false
    }

    private fun setErrorState(error: CommonError) {
        viewState.isLoading.value = false
        viewState.hasError.value = true
    }

    private fun setSuccessState() {
        viewState.isLoading.value = false
        viewState.hasError.value = false
    }

    private fun onLoadSuccess(settings: UserThemePreferenceModel) {
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
}
