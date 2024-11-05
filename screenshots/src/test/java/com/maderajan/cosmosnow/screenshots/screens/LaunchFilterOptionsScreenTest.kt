package com.maderajan.cosmosnow.screenshots.screens

import androidx.compose.runtime.Composable
import com.maderajan.cosmosnow.feature.search.filteroptions.launch.LaunchFilterOptionsScreenPreview
import com.maderajan.cosmosnow.screenshots.BaseComposableScreenshotTest

class LaunchFilterOptionsScreenTest: BaseComposableScreenshotTest() {

    @Composable
    override fun Compose(isDarkTheme: Boolean) {
        LaunchFilterOptionsScreenPreview(isDarkTheme)
    }
}