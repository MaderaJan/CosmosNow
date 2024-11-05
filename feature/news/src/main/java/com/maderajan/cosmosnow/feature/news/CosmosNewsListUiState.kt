package com.maderajan.cosmosnow.feature.news

import com.maderajan.cosmosnow.core.viewmodel.UiState
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews

data class CosmosNewsListUiState(
    val news: List<CosmosNews> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
): UiState