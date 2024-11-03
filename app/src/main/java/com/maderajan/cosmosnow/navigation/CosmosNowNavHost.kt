package com.maderajan.cosmosnow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.feature.news.CosmosNewsListRoute
import com.maderajan.cosmosnow.feature.newsdetail.CosmosNewsDetailRoute
import com.maderajan.cosmosnow.feature.search.SearchNewsScreen

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

        composable<CosmosScreens.CosmosNewsDetail> { backStackEntry ->
            val args = backStackEntry.toRoute<CosmosScreens.CosmosNewsDetail>()
            CosmosNewsDetailRoute(args.title)
        }

        composable<CosmosScreens.SearchNews> {
            SearchNewsScreen()
        }
    }
}