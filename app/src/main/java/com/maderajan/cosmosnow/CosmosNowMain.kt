package com.maderajan.cosmosnow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.maderajan.cosmosnow.feature.news.COSMOS_NEWS_ROUTE
import com.maderajan.cosmosnow.feature.news.cosmosNewsScreen
import com.maderajan.cosmosnow.navigation.BottomNavigationItems

@Composable
fun CosmosNowMain() {
    Scaffold(
        bottomBar = {
            CosmosNowBottomNavigation()
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            CosmosNowNavHost()
        }
    }
}

@Composable
fun CosmosNowBottomNavigation() {
    val selectedItem = rememberSaveable { mutableIntStateOf(0) }

    NavigationBar {
        BottomNavigationItems.entries.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem.intValue == index,
                onClick = {
                    selectedItem.intValue = index

                    //  TODO do something with navigation
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.name,
                    )
                }
            )
        }
    }
}

// TODO časem možná přesunout
@Composable
fun CosmosNowNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = COSMOS_NEWS_ROUTE
    ) {
        cosmosNewsScreen()
    }
}