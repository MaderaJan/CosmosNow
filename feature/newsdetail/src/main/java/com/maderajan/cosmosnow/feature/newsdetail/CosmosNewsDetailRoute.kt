package com.maderajan.cosmosnow.feature.newsdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews

@Composable
fun CosmosNewsDetailRoute(
    cosmosNews: CosmosNews,
    cosmosNewsDetailViewModel: CosmosNewsDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        cosmosNewsDetailViewModel.dispatch(CosmosNewsDetailUiAction.ProvideData(cosmosNews))
    }

    CosmosNewsDetailScreen(
        uiState = cosmosNewsDetailViewModel.uiState.collectAsState().value,
        dispatchAction = cosmosNewsDetailViewModel::dispatch
    )
}