package br.com.recipebook.utilityandroid.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow

@ExperimentalCoroutinesApi
abstract class BaseViewModel<ViewState, Action, Command> : ViewModel() {
    abstract val viewState: ViewState
    protected val commandSender = BroadcastChannel<Command>(Channel.BUFFERED)
    val commandReceiver = commandSender.asFlow()

    abstract fun dispatchAction(action: Action)
}
