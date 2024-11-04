package com.maderajan.cosmosnow.feature.search

import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.data.model.comosnews.SearchDate

sealed interface SearchNewsUiAction : UiAction {
    data class SearchTextChanged(val text: String) : SearchNewsUiAction
    data object OpenNewsSiteOptions : SearchNewsUiAction
    data object OpenNewsTypeOptions : SearchNewsUiAction
    data class NewsSitesSelected(val newsSites: List<String>) : SearchNewsUiAction
    data class TypesChanged(val types: List<CosmosNewsType>) : SearchNewsUiAction
    data class DateSelected(val date: SearchDate?) : SearchNewsUiAction
    data object OpenDateSelect : SearchNewsUiAction
    data object OpenLaunchOptions : SearchNewsUiAction
    data class LaunchChanged(val hasLaunch: Boolean?) : SearchNewsUiAction
    data object Search : SearchNewsUiAction

    data class OpenNews(val cosmosNews: CosmosNews) : SearchNewsUiAction
    data class BookMarkNews(val cosmosNews: CosmosNews) : SearchNewsUiAction
}