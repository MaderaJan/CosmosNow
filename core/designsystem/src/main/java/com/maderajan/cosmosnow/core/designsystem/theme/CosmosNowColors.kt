package com.maderajan.cosmosnow.core.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val blue200 = Color(0xFFD9E4F9)
val blue300 = Color(0xFFB3CCF2)
val blue400 = Color(0xFF4D82E1)
val blue500 = Color(0xFF105BD8)

val grey200 = Color(0xFFF6F7FA)
val grey800 = Color(0xFF1A1915)
val grey500 = Color(0xFFAEB3BA)

internal val lightBasicPalette = lightColorScheme(
    primary = blue500,
    surface = Color.White,
    surfaceContainer = grey200,
    secondaryContainer = blue300,
    onSurface = Color.Black,
    onSurfaceVariant = grey500,
    background = Color.White,
)

internal val darkBasicPalette = darkColorScheme(
    primary = blue500,
    surfaceContainer = grey800,
    secondaryContainer = blue500,
    onSurface = Color.White,
    onSurfaceVariant = grey500,
    background = Color.Black,
)