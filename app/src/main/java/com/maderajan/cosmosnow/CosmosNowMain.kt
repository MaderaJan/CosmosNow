package com.maderajan.cosmosnow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.maderajan.cosmosnow.navigation.BottomNavigationItems
import com.maderajan.cosmosnow.navigation.CosmosNowNavHost

@Composable
fun CosmosNowMain() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            CosmosNowBottomNavigation(navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            CosmosNowNavHost(navController)
        }
    }
}

@Composable
fun CosmosNowBottomNavigation(navController: NavController) {
    val selectedItem = rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(
        modifier = Modifier.height(86.dp)
    ) {
        BottomNavigationItems.entries.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem.intValue == index,
                onClick = {
                    selectedItem.intValue = index

                    val bottomNavigationDestinationOptions = navOptions {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                    navController.navigate(item.screen, bottomNavigationDestinationOptions)
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

