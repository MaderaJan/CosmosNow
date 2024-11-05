package com.maderajan.cosmosnow.screenshots

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Density
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
abstract class BaseComposableScreenshotTest {
    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig = DeviceConfig.PIXEL_5.copy(
                softButtons = false,
                nightMode = NightMode.NOTNIGHT,
                fontScale = 1.0f,
                locale = "en",
            ),
            renderingMode = SessionParams.RenderingMode.NORMAL,
        )

    @Test
    fun snapshot(
        @TestParameter(value = ["1.0", "1.5"]) fontScale: Float,
        @TestParameter(value = ["light", "dark"]) theme: String,
    ) {
        paparazzi.snapshot {
            CompositionLocalProvider(
                LocalInspectionMode provides true,
                LocalDensity provides Density(
                    density = LocalDensity.current.density,
                    fontScale = fontScale,
                ),
            ) {
                val darkTheme = theme == "dark"
                Compose(darkTheme)
            }
        }
    }

    @Composable
    abstract fun Compose(isDarkTheme: Boolean)
}