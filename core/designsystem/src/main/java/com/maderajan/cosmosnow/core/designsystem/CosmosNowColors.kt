package com.maderajan.cosmosnow.core.designsystem

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

val blue500 = Color(0xFF105BD8)

@Immutable
data class CosmosNowColors(
    val blue500: Color,
    val whiteConstant: Color,
    val blackConstant: Color,
) {

    companion object {

        @Composable
        public fun defaultDarkColors(): CosmosNowColors = CosmosNowColors(
            blue500 = blue500,
            whiteConstant = Color.White,
            blackConstant = Color.Black,
        )

        @Composable
        public fun defaultLightColors(): CosmosNowColors = CosmosNowColors(
            blue500 = blue500,
            whiteConstant = Color.White,
            blackConstant = Color.Black,
        )
    }
}

public object CosmosTheme {

    public val colors: CosmosNowColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
}

internal val lightBasicPalette = lightColorScheme(
    primary = blue500,
    background = Color.White,
)

internal val darkBasicPalette = darkColorScheme(
    primary = blue500,
    background = Color.Black,
)