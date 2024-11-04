package com.maderajan.cosmosnow.core.navigation

sealed interface NavigationCommand {
    data object NavigateUp : NavigationCommand

    data class NavigateToRoute(val route: CosmosScreens) : NavigationCommand

    data class NavigateUpWithResult<R>(val key: String, val result: R) : NavigationCommand
}