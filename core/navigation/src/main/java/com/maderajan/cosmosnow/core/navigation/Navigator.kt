package com.maderajan.cosmosnow.core.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableSharedFlow
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

//class CustomNavType<T : Parcelable>(
//    private val clazz: Class<T>,
//    private val serializer: KSerializer<T>,
//) : NavType<T>(isNullableAllowed = false) {
//    override fun get(bundle: Bundle, key: String): T? =
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            bundle.getParcelable(key, clazz) as T
//        } else {
//            @Suppress("DEPRECATION")
//            bundle.getParcelable(key)
//        }
//
//    override fun put(bundle: Bundle, key: String, value: T) =
//        bundle.putParcelable(key, value)
//
//    override fun parseValue(value: String): T = Json.decodeFromString(serializer, value)
//
//    override fun serializeAsValue(value: T): String = Json.encodeToString(serializer, value)
//
//    override val name: String = clazz.name
//
//    companion object {
//        inline fun <reified T : Parcelable> getCustomNavTypeMap(serializer: KSerializer<T>): Map<KType, CustomNavType<T>> =
//            mapOf(
//                typeOf<T>() to CustomNavType(T::class.java, serializer),
//            )
//    }
//}