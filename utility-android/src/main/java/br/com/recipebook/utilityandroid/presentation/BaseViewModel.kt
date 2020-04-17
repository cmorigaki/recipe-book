package br.com.recipebook.utilityandroid.presentation

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ViewState, ViewAction> : ViewModel() {
    abstract val viewState: ViewState
    abstract fun dispatchViewAction(action: ViewAction)
}