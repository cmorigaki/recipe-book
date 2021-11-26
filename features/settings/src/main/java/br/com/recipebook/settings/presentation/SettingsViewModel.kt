package br.com.recipebook.settings.presentation

import androidx.lifecycle.viewModelScope
import br.com.recipebook.analytics.Analytics
import br.com.recipebook.di.BuildConfiguration
import br.com.recipebook.settings.analytics.ViewSettingsEvent
import br.com.recipebook.settings.domain.model.SettingsItemModel
import br.com.recipebook.settings.domain.usecase.GetSettingsUseCase
import br.com.recipebook.settings.presentation.model.SettingsItem
import br.com.recipebook.utilityandroid.presentation.BaseViewModel
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SettingsViewModel(
    override val viewState: MutableStateFlow<SettingsViewState>,
    private val getSettingsList: GetSettingsUseCase,
    private val analytics: Analytics,
    private val buildConfiguration: BuildConfiguration,
) : BaseViewModel<MutableStateFlow<SettingsViewState>, SettingsAction, SettingsCommand>() {

    init {
        viewModelScope.launch {
            viewState.value = SettingsViewState.Loading(buildConfiguration.appInfo.version)
            getSettingsList().onSuccess(::onLoadSuccess).onFailure { onLoadError() }
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

    private fun onLoadSuccess(settings: List<SettingsItemModel>) {
        sendViewEvent(true)
        val listItems = settings.map {
            SettingsItem(id = it.id, title = it.title, navIntent = it.navIntent)
        }
        viewState.value = SettingsViewState.Loaded(
            listItems = listItems,
            appVersion = buildConfiguration.appInfo.version,
        )
    }

    private fun onLoadError() {
        sendViewEvent(false)
        viewState.value = SettingsViewState.Error(buildConfiguration.appInfo.version)
    }

    private fun sendViewEvent(success: Boolean) {
        analytics.sendEvent(ViewSettingsEvent(success))
    }
}
