package com.maderajan.cosmosnow.core.navigation

import androidx.navigation.NavDestination
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import kotlinx.serialization.Serializable

sealed interface CosmosScreens {

    @Serializable
    data object CosmosNewsList : CosmosScreens

    @Serializable
    data object SearchNews : CosmosScreens

    @Serializable
    data class CosmosNewsDetail(val cosmosNews: CosmosNews) : CosmosScreens

    @Serializable
    data object Bookmarks : CosmosScreens

    companion object {
        fun isTopLevelDestination(destination: NavDestination?): Boolean =
            destination?.route?.contains("com.maderajan.cosmosnow.core.navigation.CosmosScreens.CosmosNewsDetail") != null
    }
}