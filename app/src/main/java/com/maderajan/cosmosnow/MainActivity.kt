package com.maderajan.cosmosnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.maderajan.cosmosnow.feature.news.CosmosNewsListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CosmosNowTheme {
                CosmosNewsListScreen()
            }
        }
    }
}

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

@Immutable
public data class CosmosNowColors(
    val absoluteWhite: Color,
    val absoluteBlack: Color,
) {

    public companion object {
        /**
         * Provides the default colors for the light mode of the app.
         *
         * @return A [CosmosNowColors] instance holding our color palette.
         */
        @Composable
        public fun defaultDarkColors(): CosmosNowColors = CosmosNowColors(
            absoluteWhite = colorResource(id = R.color.white),
            absoluteBlack = colorResource(id = R.color.black),
        )

        /**
         * Provides the default colors for the light mode of the app.
         *
         * @return A [CosmosNowColors] instance holding our color palette.
         */
        @Composable
        public fun defaultLightColors(): CosmosNowColors = CosmosNowColors(
            absoluteWhite = colorResource(id = R.color.white),
            absoluteBlack = colorResource(id = R.color.black),
        )
    }
}

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
                    color = colorResource(id = R.color.black),
                    tonalElevation = 0.dp,
                )
            } else {
                CosmosNowBackground(
                    color = colorResource(id = R.color.white),
                    tonalElevation = 0.dp,
                )
            }
        }
    }
}

public val LocalBackgroundTheme: ProvidableCompositionLocal<CosmosNowBackground> =
    staticCompositionLocalOf { CosmosNowBackground() }