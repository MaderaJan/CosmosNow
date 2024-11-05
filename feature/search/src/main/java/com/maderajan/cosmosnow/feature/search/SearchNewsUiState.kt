package com.maderajan.cosmosnow.feature.search

import com.maderajan.cosmosnow.core.viewmodel.UiState
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.data.model.comosnews.SearchDate

data class SearchNewsUiState(
    val searchText: String = "",
    val newsSites: List<String> = emptyList(),
    val types: List<CosmosNewsType> = emptyList(),
    val date: SearchDate? = null,
    val hasLaunch: Boolean? = null,
    val news: List<CosmosNews> = emptyList(),
    val isSearching: Boolean = false,
    val isError: Boolean = false,
): UiState