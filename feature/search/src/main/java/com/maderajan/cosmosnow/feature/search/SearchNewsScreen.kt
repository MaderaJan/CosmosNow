package com.maderajan.cosmosnow.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.feature.search.components.CosmosNowSearchFilterBar

@Composable
fun SearchNewsRoute(
    viewModel: SearchNewsViewModel = hiltViewModel()
) {
    SearchNewsScreen(
        uiState = viewModel.uiState.collectAsState().value,
        dispatchAction = viewModel::dispatch
    )
}

@Composable
fun SearchNewsScreen(
    uiState: SearchNewsUiState,
    dispatchAction: (SearchNewsUiAction) -> Unit
) {
    Scaffold(
        topBar = {
            CosmosNowSearchFilterBar(uiState, dispatchAction)
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues))
        }
    )
}

@Preview
@Composable
fun SearchNewsScreenPreview() {
    CosmosNowTheme {
        SearchNewsScreen(
            uiState = SearchNewsUiState(),
            dispatchAction = {}
        )
    }
}