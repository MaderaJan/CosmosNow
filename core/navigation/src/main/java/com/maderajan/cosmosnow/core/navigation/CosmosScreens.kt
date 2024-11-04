package com.maderajan.cosmosnow.core.navigation

import androidx.navigation.NavDestination
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
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

    @Serializable
    data class SearchNewsFilterLaunch(val launch: Boolean?) : CosmosScreens {

        companion object {
            const val RESULT_KEY = "launch"
        }
    }

    @Serializable
    data class SearchNewsFilterCosmosNewsType(val types: List<CosmosNewsType>) : CosmosScreens {

        companion object {
            const val RESULT_KEY = "cosmos_news_type"
        }
    }

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