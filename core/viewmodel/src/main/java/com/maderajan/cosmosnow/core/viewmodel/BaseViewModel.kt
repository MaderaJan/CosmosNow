package com.maderajan.cosmosnow.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<A : UiAction>() : ViewModel() {

    private val _uiAction = Channel<A>(Channel.BUFFERED)

    protected abstract fun handleAction(action: A)

    init {
        viewModelScope.launch {
            _uiAction.consumeEach {
                handleAction(it)
            }
        }
    }

    open fun dispatch(newAction: A) {
        viewModelScope.launch {
            _uiAction.send(newAction)
        }
    }
}

abstract class BaseMviViewModel<S: UiState, A : UiAction>(
    defaultState: S,
) : BaseViewModel<A>() {

    // UI-STATE
    private val _uiState = MutableStateFlow(defaultState)
    val uiState: StateFlow<S>
        get() = _uiState

    protected fun setUiState(state: S) {
        _uiState.value = state
    }

    protected fun setUiState(reducer: S.() -> (S)) {
        val updatedState = reducer(uiState.value)
        setUiState(updatedState)
    }
}

interface UiState

interface UiAction
