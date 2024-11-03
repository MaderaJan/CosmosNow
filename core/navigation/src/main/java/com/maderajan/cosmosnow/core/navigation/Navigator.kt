package com.maderajan.cosmosnow.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavType
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    private val _commands = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = Int.MAX_VALUE)

    fun navigate(command: NavigationCommand) {
        _commands.tryEmit(command)
    }

    fun navigateUp() {
        _commands.tryEmit(NavigationCommand.NavigateUp)
    }

    suspend fun handleNavigationCommands(navController: NavController) {
        _commands.collect { navController.handleComposeNavigationCommand(it) }
    }

    private fun NavController.handleComposeNavigationCommand(navigationCommand: NavigationCommand) {
        when (navigationCommand) {
            is NavigationCommand.NavigateToRoute -> navigate(navigationCommand.route)
            NavigationCommand.NavigateUp -> navigateUp()
        }
    }
}

sealed interface NavigationCommand {
    data object NavigateUp : NavigationCommand
    data class NavigateToRoute(val route: CosmosScreens) : NavigationCommand
}

object CustomNavType {

    val cosmosNewsType = object : NavType<CosmosNews>(isNullableAllowed = false) {

        override fun get(bundle: Bundle, key: String): CosmosNews? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun serializeAsValue(value: CosmosNews): String =
            Uri.encode(Json.encodeToString(value))

        override fun parseValue(value: String): CosmosNews =
            Json.decodeFromString(Uri.decode(value))

        override fun put(bundle: Bundle, key: String, value: CosmosNews) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}