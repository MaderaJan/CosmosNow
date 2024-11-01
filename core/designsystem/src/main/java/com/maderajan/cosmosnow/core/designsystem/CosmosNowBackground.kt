package com.maderajan.cosmosnow.core.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public val LocalBackgroundTheme: ProvidableCompositionLocal<CosmosNowBackground> =
    staticCompositionLocalOf { CosmosNowBackground() }

@Immutable
public data class CosmosNowBackground(
    val color: Color = Color.Unspecified,
    val tonalElevation: Dp = Dp.Unspecified,
) {
    public companion object {
        @Composable
        public fun defaultBackground(darkTheme: Boolean): CosmosNowBackground {
            return if (darkTheme) {
                CosmosNowBackground(
                    color = Color.Black,
                    tonalElevation = 0.dp,
                )
            } else {
                CosmosNowBackground(
                    color = Color.White,
                    tonalElevation = 0.dp,
                )
            }
        }
    }
}