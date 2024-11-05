package com.maderajan.cosmosnow.feature.newsdetail.changefont

import androidx.lifecycle.viewModelScope
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.domain.cosmosnews.NewsFontUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeNewsFontViewModel @Inject constructor(
    private val newsFontUseCase: NewsFontUseCase,
    private val navigator: Navigator,
) : BaseViewModel<ChangeNewsFontUiAction>() {

    val uiState = newsFontUseCase.getFontSize()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 16
        )

    override fun handleAction(action: ChangeNewsFontUiAction) {
        when (action) {
            ChangeNewsFontUiAction.ChangeNewsFont -> {
                viewModelScope.launch {
                    newsFontUseCase.increaseFont(uiState.value)
                }
            }

            ChangeNewsFontUiAction.DecreaseFont -> {
                viewModelScope.launch {
                    newsFontUseCase.decreaseFont(uiState.value)
                }
            }

            ChangeNewsFontUiAction.NavigateBack -> {
                navigator.navigateUp()
            }
        }
    }
}