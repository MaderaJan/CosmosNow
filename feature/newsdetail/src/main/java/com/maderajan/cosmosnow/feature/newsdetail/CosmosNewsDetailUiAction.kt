package com.maderajan.cosmosnow.feature.newsdetail

import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews

sealed interface CosmosNewsDetailUiAction : UiAction {
    data class ProvideData(val cosmosNews: CosmosNews) : CosmosNewsDetailUiAction
    data object OpenIncreaseFont : CosmosNewsDetailUiAction
    data object NavigateBack : CosmosNewsDetailUiAction
    data class BookmarkNews(val cosmosNews: CosmosNews)  : CosmosNewsDetailUiAction
}