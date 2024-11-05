package com.maderajan.cosmosnow.feature.newsdetail.changefont

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeNewsFontRoute(
    viewModel: ChangeNewsFontViewModel = hiltViewModel()
) {
    ModalBottomSheet(
        onDismissRequest = {
            viewModel.dispatch(ChangeNewsFontUiAction.NavigateBack)
        },
        content = {
            ChangeNewsFontScreen(
                fontSize = viewModel.uiState.collectAsState().value,
                dispatchAction = viewModel::dispatch
            )
        },
    )
}