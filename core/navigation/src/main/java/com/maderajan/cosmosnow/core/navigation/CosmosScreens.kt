package com.maderajan.cosmosnow.core.navigation

import kotlinx.serialization.Serializable

sealed interface CosmosScreens {

    @Serializable
    data object CosmosNews: CosmosScreens

    @Serializable
    data object SearchNews: CosmosScreens

    @Serializable
    data object Bookmarks: CosmosScreens
}