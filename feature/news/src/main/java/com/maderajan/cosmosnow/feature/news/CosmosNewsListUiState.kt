package com.maderajan.cosmosnow.feature.news

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews

sealed interface CosmosNewsListUiState {

    data object Loading : CosmosNewsListUiState

    data object Error : CosmosNewsListUiState

    data class Success(val news: List<CosmosNews>) : CosmosNewsListUiState
}