package com.maderajan.cosmosnow.core.navigation

import kotlinx.serialization.Serializable

sealed interface CosmosScreens {

    @Serializable
    data object CosmosNewsList: CosmosScreens

    @Serializable
    data object SearchNews: CosmosScreens

    @Serializable
    data class CosmosNewsDetail(val title: String): CosmosScreens

    @Serializable
    data object Bookmarks: CosmosScreens
}