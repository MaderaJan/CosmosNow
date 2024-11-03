package com.maderajan.cosmosnow.feature.newsdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
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

@Preview
@Composable
fun CosmosNewsDetailScreenPreview() {
    CosmosNowTheme {
        CosmosNewsDetailScreen(
            uiState = CosmosNewsDetailUiState.Success(
                cosmosNews = CosmosNews.fake(title = "Crew-8 on the Way Home at Last")
            ),
            dispatchAction = {}
        )
    }
}

// TODO Move
fun CosmosNewsType.getPresentableNameRes(): Int =
    when (this) {
        CosmosNewsType.ARTICLE -> R.string.news_type_article
        CosmosNewsType.BLOG -> R.string.news_type_blog
        CosmosNewsType.REPORT -> R.string.news_type_report
    }