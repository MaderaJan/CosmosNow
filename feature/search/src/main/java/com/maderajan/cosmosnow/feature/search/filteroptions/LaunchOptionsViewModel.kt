package com.maderajan.cosmosnow.feature.search.filteroptions

import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.NavigationCommand
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.core.viewmodel.UiAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LaunchOptionsViewModel @Inject constructor(
    private val navigator: Navigator
) : BaseViewModel<LaunchOptionsUiAction>() {

    val uiState = MutableStateFlow<Boolean?>(null)

    override fun handleAction(action: LaunchOptionsUiAction) {
        when (action) {
            is LaunchOptionsUiAction.ProvideData -> {
                uiState.value = action.hasLaunch
            }

            LaunchOptionsUiAction.NoneSelected -> {
                uiState.value = null
            }

            LaunchOptionsUiAction.LaunchedSelected -> {
                uiState.value = true
            }

            LaunchOptionsUiAction.NotLaunchedSelected -> {
                uiState.value = false
            }

            LaunchOptionsUiAction.NavigateBack -> {
                navigator.navigate(NavigationCommand.NavigateUp)
            }

            LaunchOptionsUiAction.ApplyFilter -> {
                navigator.navigate(NavigationCommand.NavigateUpWithResult(CosmosScreens.SearchNewsFilterLaunch.RESULT_KEY, uiState.value))
            }
        }
    }
}

sealed interface LaunchOptionsUiAction : UiAction {
    data class ProvideData(val hasLaunch: Boolean?): LaunchOptionsUiAction
    data object NavigateBack : LaunchOptionsUiAction
    data object NoneSelected : LaunchOptionsUiAction
    data object LaunchedSelected : LaunchOptionsUiAction
    data object NotLaunchedSelected : LaunchOptionsUiAction
    data object ApplyFilter : LaunchOptionsUiAction
}