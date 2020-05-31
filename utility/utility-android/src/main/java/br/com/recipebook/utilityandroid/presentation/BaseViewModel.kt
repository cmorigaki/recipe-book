package br.com.recipebook.utilityandroid.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ViewState, ActionFromView, ActionToView> : ViewModel() {
    abstract val viewState: ViewState

    protected val _actionToView = MutableLiveData<ActionToView>()
    val actionToView: LiveData<ActionToView> get() = _actionToView

    abstract fun dispatchAction(action: ActionFromView)
}