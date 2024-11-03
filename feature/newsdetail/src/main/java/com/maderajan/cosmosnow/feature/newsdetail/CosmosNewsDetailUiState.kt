package com.maderajan.cosmosnow.feature.newsdetail

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews

sealed interface CosmosNewsDetailUiState {
    data object Loading : CosmosNewsDetailUiState
    data class Success(val cosmosNews: CosmosNews) : CosmosNewsDetailUiState
}