package br.com.recipebook.settings.presentation

import androidx.lifecycle.viewModelScope
import br.com.recipebook.settings.domain.model.SettingsItemModel
import br.com.recipebook.settings.domain.usecase.GetSettingsUseCase
import br.com.recipebook.settings.presentation.model.SettingsItem
import br.com.recipebook.utilityandroid.presentation.BaseViewModel
import br.com.recipebook.utilitykotlin.CommonError
import kotlinx.coroutines.launch

class SettingsViewModel(
    override val viewState: SettingsViewState,
    private val getSettingsList: GetSettingsUseCase
) : BaseViewModel<SettingsViewState, SettingsActionFromView, SettingsActionToView>() {

    init {
        viewModelScope.launch {
            setLoadingState()
            getSettingsList().mapSuccess(::onLoadSuccess).mapError(::setErrorState)
        }
    }

    override fun dispatchAction(action: SettingsActionFromView) {
        when (action) {
            is SettingsActionFromView.ItemClick -> _actionToView.value =
                SettingsActionToView.OpenItem(action.settingsItem.navIntent)
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

    private fun onLoadSuccess(settings: List<SettingsItemModel>) {
        viewState.listItems.value = settings.map {
            SettingsItem(id = it.id, title = it.title, navIntent = it.navIntent)
        }
        setSuccessState()
    }
}
