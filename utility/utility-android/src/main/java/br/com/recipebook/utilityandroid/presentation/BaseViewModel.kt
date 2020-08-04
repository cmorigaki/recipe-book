package br.com.recipebook.utilityandroid.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow

@ExperimentalCoroutinesApi
abstract class BaseViewModel<ViewState, ActionFromView, ActionToView> : ViewModel() {
    abstract val viewState: ViewState
    val actionToView = BroadcastChannel<ActionToView>(Channel.BUFFERED)
    val commands = actionToView.asFlow()

    abstract fun dispatchAction(action: ActionFromView)
}
