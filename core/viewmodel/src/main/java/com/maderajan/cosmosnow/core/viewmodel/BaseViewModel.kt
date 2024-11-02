package com.maderajan.cosmosnow.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
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

interface UiState

interface UiEffect

interface UiAction