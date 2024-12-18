package com.maderajan.cosmosnow.feature.newsdetail

import com.maderajan.cosmosnow.core.viewmodel.UiState
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews

data class CosmosNewsDetailUiState(
    val cosmosNews: CosmosNews = CosmosNews.fake(),
    val fontSize: Int = 16
): UiState