package com.maderajan.cosmosnow.feature.search.filteroptions.sites

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.maderajan.cosmosnow.core.designsystem.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsSitesFilterOptionsRoute(
    newsSites: List<String>,
    viewModel: NewsSitesFilterOptionsMviViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.dispatch(NewsSitesFilterOptionsUiAction.ProvideData(newsSites = newsSites))
    }

    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        onDismissRequest = {
            viewModel.dispatch(NewsSitesFilterOptionsUiAction.NavigateBack)
        },
        content = {
            NewsSitesFilterOptionsScreen(
                uiState = viewModel.uiState.collectAsState().value,
                dispatchAction = viewModel::dispatch
            )
        },
        modifier = Modifier.padding(top = MaterialTheme.spacing.extraLarge)
    )
}