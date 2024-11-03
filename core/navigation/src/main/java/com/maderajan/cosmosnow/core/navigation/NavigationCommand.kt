package com.maderajan.cosmosnow.core.navigation

sealed interface NavigationCommand {
    data object NavigateUp : NavigationCommand
    data class NavigateToRoute(val route: CosmosScreens) : NavigationCommand
}