package com.maderajan.cosmosnow.core.navigation

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import kotlinx.serialization.Serializable

sealed interface CosmosScreens {

    @Serializable
    data object CosmosNewsList: CosmosScreens

    @Serializable
    data object SearchNews: CosmosScreens

    @Serializable
    data class CosmosNewsDetail(val cosmosNews: CosmosNews): CosmosScreens

    @Serializable
    data object Bookmarks: CosmosScreens
}