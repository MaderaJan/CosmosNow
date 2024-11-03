package com.maderajan.cosmosnow.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.test.FakeImage

@Composable
public fun CosmosNowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    background: CosmosNowBackground = CosmosNowBackground.defaultBackground(darkTheme),
    content: @Composable () -> Unit,
) {
    val basicPalette = if (darkTheme) darkBasicPalette else lightBasicPalette

    CompositionLocalProvider(
        LocalBackgroundTheme provides background,
        LocalAsyncImagePreviewHandler provides previewHandler
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

val previewHandler = AsyncImagePreviewHandler {
    FakeImage(color = 0xFFFF0000.toInt())
}