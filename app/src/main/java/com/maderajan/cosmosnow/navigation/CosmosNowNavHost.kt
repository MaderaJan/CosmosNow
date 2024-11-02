package com.maderajan.cosmosnow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.feature.news.CosmosNewsListRoute
import com.maderajan.cosmosnow.feature.search.SearchNewsScreen

@Composable
fun CosmosNowNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = CosmosScreens.CosmosNews
    ) {
        composable<CosmosScreens.CosmosNews> {
            CosmosNewsListRoute()
        }

        composable<CosmosScreens.SearchNews> {
            SearchNewsScreen()
        }
    }
}