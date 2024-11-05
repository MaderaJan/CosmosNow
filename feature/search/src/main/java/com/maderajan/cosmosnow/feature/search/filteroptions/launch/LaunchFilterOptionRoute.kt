package com.maderajan.cosmosnow.feature.search.filteroptions.launch

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchFilterOptionRoute(
    hasLaunch: Boolean?,
    viewModel: LaunchOptionsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.dispatch(LaunchOptionsUiAction.ProvideData(hasLaunch = hasLaunch))
    }

    ModalBottomSheet(
        onDismissRequest = {
            viewModel.dispatch(LaunchOptionsUiAction.NavigateBack)
        },
        content = {
            LaunchFilterOptionsScreen(
                launchBoolean = viewModel.uiState.collectAsState().value,
                dispatchAction = viewModel::dispatch
            )
        }
    )
}