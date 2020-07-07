package br.com.recipebook.settings.presentation

import androidx.lifecycle.MutableLiveData
import br.com.recipebook.settings.presentation.model.SettingsItem

class SettingsViewState {
    val isLoading = MutableLiveData<Boolean>()

    val hasError = MutableLiveData<Boolean>()

    val listItems = MutableLiveData<List<SettingsItem>>()
}
