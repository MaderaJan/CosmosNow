package com.maderajan.cosmosnow.core.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
public data class CosmosNowColors(
    val whiteConstant: Color,
    val blackConstant: Color,
) {

    public companion object {
        /**
         * Provides the default colors for the light mode of the app.
         *
         * @return A [CosmosNowColors] instance holding our color palette.
         */
        @Composable
        public fun defaultDarkColors(): CosmosNowColors = CosmosNowColors(
            whiteConstant = Color.White,
            blackConstant = Color.Black,
        )

        /**
         * Provides the default colors for the light mode of the app.
         *
         * @return A [CosmosNowColors] instance holding our color palette.
         */
        @Composable
        public fun defaultLightColors(): CosmosNowColors = CosmosNowColors(
            whiteConstant = Color.White,
            blackConstant = Color.Black,
        )
    }
}

