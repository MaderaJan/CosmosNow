package com.maderajan.cosmosnow.core.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier

val LocalColors = compositionLocalOf<CosmosNowColors> {
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
    val basicPalette = if (darkTheme) darkBasicPalette else lightBasicPalette

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalBackgroundTheme provides background,
    ) {
        MaterialTheme(
            colorScheme = basicPalette
        ) {
            Box(
                modifier = Modifier
                    .background(background.color)
            ) {
                content()
            }
        }
    }
}
