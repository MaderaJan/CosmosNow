package com.maderajan.cosmosnow.feature.newsdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType

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

// TODO Move
fun CosmosNewsType.getPresentableNameRes(): Int =
    when (this) {
        CosmosNewsType.ARTICLE -> R.string.news_type_article
        CosmosNewsType.BLOG -> R.string.news_type_blog
        CosmosNewsType.REPORT -> R.string.news_type_report
    }