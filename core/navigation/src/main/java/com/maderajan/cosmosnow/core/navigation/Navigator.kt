package com.maderajan.cosmosnow.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavType
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

    fun <R> navigateUpWithResult(key: String, result: R) {
        _commands.tryEmit(NavigationCommand.NavigateUpWithResult(key = key, result = result))
    }

    fun navigateUp() {
        _commands.tryEmit(NavigationCommand.NavigateUp)
    }

    suspend fun handleNavigationCommands(navController: NavController) {
        _commands.collect { navController.handleComposeNavigationCommand(it) }
    }

    private fun NavController.handleComposeNavigationCommand(navigationCommand: NavigationCommand) {
        when (navigationCommand) {
            is NavigationCommand.NavigateToRoute -> {
                navigate(navigationCommand.route)
            }

            is NavigationCommand.NavigateUpWithResult<*> -> {
                navUpWithResult(navigationCommand)
            }

            NavigationCommand.NavigateUp -> {
                navigateUp()
            }
        }
    }

    private fun NavController.navUpWithResult(navigationCommand: NavigationCommand.NavigateUpWithResult<*>) {
        val backStackEntry = previousBackStackEntry

        backStackEntry?.savedStateHandle?.set(
            navigationCommand.key,
            navigationCommand.result,
        )

        navigateUp()
    }
}

inline fun <reified T> navTypeOf(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let(json::decodeFromString)

    override fun parseValue(value: String): T = json.decodeFromString(Uri.decode(value))

    override fun serializeAsValue(value: T): String = Uri.encode(json.encodeToString(value))

    override fun put(bundle: Bundle, key: String, value: T) =
        bundle.putString(key, json.encodeToString(value))
}