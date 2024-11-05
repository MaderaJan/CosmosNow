package com.maderajan.cosmosnow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.toRoute
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.navTypeOf
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.feature.bookmarks.BookmarksRoute
import com.maderajan.cosmosnow.feature.news.CosmosNewsListRoute
import com.maderajan.cosmosnow.feature.newsdetail.CosmosNewsDetailRoute
import com.maderajan.cosmosnow.feature.search.SearchNewsRoute
import com.maderajan.cosmosnow.feature.search.filteroptions.date.DateSelectedRoute
import com.maderajan.cosmosnow.feature.search.filteroptions.launch.LaunchFilterOptionRoute
import com.maderajan.cosmosnow.feature.search.filteroptions.sites.NewsSitesFilterOptionsRoute
import com.maderajan.cosmosnow.feature.search.filteroptions.type.CosmosNewsTypeFilterOptionsRoute
import kotlin.reflect.typeOf

@Composable
fun CosmosNowNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = CosmosScreens.CosmosNewsList
    ) {
        composable<CosmosScreens.CosmosNewsList> {
            CosmosNewsListRoute()
        }

        composable<CosmosScreens.CosmosNewsDetail>(
            typeMap = mapOf(typeOf<CosmosNews>() to navTypeOf<CosmosNews>())
        ) { backStackEntry ->
            val cosmosNews = backStackEntry.toRoute<CosmosScreens.CosmosNewsDetail>().cosmosNews
            CosmosNewsDetailRoute(cosmosNews)
        }

        composable<CosmosScreens.SearchNews> { backStackEntry ->
            SearchNewsRoute(
                savedStateHandle = backStackEntry.savedStateHandle
            )
        }

        composable<CosmosScreens.Bookmarks> {
            BookmarksRoute()
        }

        dialog<CosmosScreens.SearchNewsFilterLaunch> { backStackEntry ->
            LaunchFilterOptionRoute(
                hasLaunch = backStackEntry.toRoute<CosmosScreens.SearchNewsFilterLaunch>().launch
            )
        }

        dialog<CosmosScreens.SearchNewsFilterCosmosNewsType>(
            typeMap = mapOf(typeOf<List<CosmosNewsType>>() to navTypeOf<List<CosmosNewsType>>())
        ) { backStackEntry ->
            CosmosNewsTypeFilterOptionsRoute(
                types = backStackEntry.toRoute<CosmosScreens.SearchNewsFilterCosmosNewsType>().types
            )
        }

        dialog<CosmosScreens.SearchNewsFilterDate> { backStackEntry ->
            DateSelectedRoute(
                date = backStackEntry.toRoute<CosmosScreens.SearchNewsFilterDate>().date
            )
        }

        dialog<CosmosScreens.SearchNewsFilterNewsSites>(
            typeMap = mapOf(typeOf<List<String>>() to navTypeOf<List<String>>())
        ) { backStackEntry ->
            NewsSitesFilterOptionsRoute(
                newsSites = backStackEntry.toRoute<CosmosScreens.SearchNewsFilterNewsSites>().newsSites
            )
        }
    }
}