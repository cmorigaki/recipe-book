package br.com.recipebook.settings.presentation

import androidx.lifecycle.viewModelScope
import br.com.recipebook.analytics.Analytics
import br.com.recipebook.di.BuildConfiguration
import br.com.recipebook.settings.analytics.ViewSettingsEvent
import br.com.recipebook.settings.domain.model.SettingsItemModel
import br.com.recipebook.settings.domain.usecase.GetSettingsUseCase
import br.com.recipebook.settings.presentation.model.SettingsItem
import br.com.recipebook.utilityandroid.presentation.BaseViewModel
import br.com.recipebook.utilitykotlin.CommonError
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SettingsViewModel(
    override val viewState: SettingsViewState,
    private val getSettingsList: GetSettingsUseCase,
    private val analytics: Analytics,
    buildConfiguration: BuildConfiguration,
) : BaseViewModel<SettingsViewState, SettingsAction, SettingsCommand>() {

    init {
        viewState.appVersion.value = buildConfiguration.appInfo.version
        viewModelScope.launch {
            setLoadingState()
            getSettingsList().mapSuccess(::onLoadSuccess).mapError(::onLoadError)
        }
    }

    override fun dispatchAction(action: SettingsAction) {
        viewModelScope.launch {
            when (action) {
                is SettingsAction.ItemClick ->
                    commandSender.send(SettingsCommand.OpenItem(action.settingsItem.navIntent))
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

    private fun onLoadSuccess(settings: List<SettingsItemModel>) {
        sendViewEvent(true)
        viewState.listItems.value = settings.map {
            SettingsItem(id = it.id, title = it.title, navIntent = it.navIntent)
        }
        setSuccessState()
    }

    private fun onLoadError(error: CommonError) {
        sendViewEvent(false)
        setErrorState()
    }

    private fun sendViewEvent(success: Boolean) {
        analytics.sendEvent(ViewSettingsEvent(success))
    }
}
