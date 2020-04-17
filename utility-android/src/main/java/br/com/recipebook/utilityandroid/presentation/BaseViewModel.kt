package br.com.recipebook.utilityandroid.presentation

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ViewState> : ViewModel() {
    abstract val viewState: ViewState
}