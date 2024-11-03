package com.maderajan.cosmosnow.feature.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CosmosNewsListRoute(
    viewModel: CosmosNewsListViewModel = hiltViewModel()
) {
    CosmosNewsListScreen(
        uiState = viewModel.uiState.collectAsState().value,
        dispatchAction = viewModel::dispatch
    )
}