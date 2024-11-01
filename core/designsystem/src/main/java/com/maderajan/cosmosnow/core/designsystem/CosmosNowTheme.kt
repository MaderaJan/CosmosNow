package com.maderajan.cosmosnow.core.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier

private val LocalColors = compositionLocalOf<CosmosNowColors> {
    error("No colors provided! Make sure to wrap all usages of CosmosNow components in CosmosNowTheme.")
}

@Composable
public fun CosmosNowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: CosmosNowColors = if (darkTheme) {
        CosmosNowColors.defaultDarkColors()
    } else {
        CosmosNowColors.defaultLightColors()
    },
    background: CosmosNowBackground = CosmosNowBackground.defaultBackground(darkTheme),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalBackgroundTheme provides background,
    ) {
        Box(
            modifier = Modifier
                .background(background.color)
        ) {
            content()
        }
    }
}