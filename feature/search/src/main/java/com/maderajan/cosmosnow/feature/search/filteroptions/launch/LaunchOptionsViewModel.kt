package com.maderajan.cosmosnow.feature.search.filteroptions.launch

import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.NavigationCommand
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
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
                navigator.navigateUpWithResult(CosmosScreens.SearchNewsFilterLaunch.RESULT_KEY, uiState.value)
            }
        }
    }
}

