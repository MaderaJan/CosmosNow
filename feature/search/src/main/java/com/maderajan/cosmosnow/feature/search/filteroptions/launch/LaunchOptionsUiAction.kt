package com.maderajan.cosmosnow.feature.search.filteroptions.launch

import com.maderajan.cosmosnow.core.viewmodel.UiAction

sealed interface LaunchOptionsUiAction : UiAction {
    data class ProvideData(val hasLaunch: Boolean?): LaunchOptionsUiAction
    data object NavigateBack : LaunchOptionsUiAction
    data object NoneSelected : LaunchOptionsUiAction
    data object LaunchedSelected : LaunchOptionsUiAction
    data object NotLaunchedSelected : LaunchOptionsUiAction
    data object ApplyFilter : LaunchOptionsUiAction
}