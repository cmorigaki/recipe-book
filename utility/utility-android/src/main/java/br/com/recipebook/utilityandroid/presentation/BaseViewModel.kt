package br.com.recipebook.utilityandroid.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel<ViewState, Action, Command> : ViewModel() {
    abstract val viewState: ViewState
    protected val commandSender = MutableSharedFlow<Command>()
    val commandReceiver = commandSender.asSharedFlow()

    abstract fun dispatchAction(action: Action)
}
