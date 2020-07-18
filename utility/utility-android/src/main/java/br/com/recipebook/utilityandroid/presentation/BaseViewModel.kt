package br.com.recipebook.utilityandroid.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel

abstract class BaseViewModel<ViewState, ActionFromView, ActionToView> : ViewModel() {
    abstract val viewState: ViewState
    val actionToView = Channel<ActionToView>()

    abstract fun dispatchAction(action: ActionFromView)
}
