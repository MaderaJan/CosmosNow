package com.maderajan.cosmosnow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.navTypeOf
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.feature.news.CosmosNewsListRoute
import com.maderajan.cosmosnow.feature.newsdetail.CosmosNewsDetailRoute
import com.maderajan.cosmosnow.feature.search.SearchNewsScreen
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

        composable<CosmosScreens.SearchNews> {
            SearchNewsScreen()
        }
    }
}