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
        private val topLevelDestination = listOf(
            "com.maderajan.cosmosnow.core.navigation.CosmosScreens.CosmosNewsList",
            "com.maderajan.cosmosnow.core.navigation.CosmosScreens.SearchNews",
            "com.maderajan.cosmosnow.core.navigation.CosmosScreens.Bookmarks"
        )

        fun isTopLevelDestination(destination: NavDestination?): Boolean =
            topLevelDestination.any {
                destination?.route?.contains(it) == true
            }
    }
}