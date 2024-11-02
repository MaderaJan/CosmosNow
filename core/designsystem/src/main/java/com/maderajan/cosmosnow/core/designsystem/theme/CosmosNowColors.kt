package com.maderajan.cosmosnow.core.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val blue500 = Color(0xFF105BD8)
val grey500 = Color(0xFFAEB3BA)

@Immutable
data class CosmosNowColors(
    val blue500: Color,
    val textSecondary: Color,
    val whiteConstant: Color,
    val blackConstant: Color,
) {

    companion object {

        @Composable
        public fun defaultDarkColors(): CosmosNowColors = CosmosNowColors(
            blue500 = blue500,
            textSecondary = grey500,
            whiteConstant = Color.White,
            blackConstant = Color.Black,
        )

        @Composable
        public fun defaultLightColors(): CosmosNowColors = CosmosNowColors(
            blue500 = blue500, // TODO dark
            textSecondary = grey500, // TODO dark
            whiteConstant = Color.White,
            blackConstant = Color.Black,
        )
    }
}

internal val lightBasicPalette = lightColorScheme(
    primary = blue500,
    onSurface = Color.Black,
    onSurfaceVariant = grey500,
    background = Color.White,
)

internal val darkBasicPalette = darkColorScheme(
    primary = blue500,
    onSurface = Color.Black, // TODO dark
    onSurfaceVariant = grey500, // TODO dark
    background = Color.Black,
)