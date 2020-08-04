package br.com.recipebook.utilityandroid.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow

@ExperimentalCoroutinesApi
abstract class BaseViewModel<ViewState, Action, Command> : ViewModel() {
    abstract val viewState: ViewState
    protected val command = BroadcastChannel<Command>(Channel.BUFFERED)
    val commandFlow = command.asFlow()

    abstract fun dispatchAction(action: Action)
}
