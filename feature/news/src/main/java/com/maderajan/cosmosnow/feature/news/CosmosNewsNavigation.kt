package com.maderajan.cosmosnow.feature.news

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val COSMOS_NEWS_ROUTE = "news"

fun NavGraphBuilder.cosmosNewsScreen() {
    composable(
        route = COSMOS_NEWS_ROUTE
    ) {
        CosmosNewsListScreen()
    }
}