package com.maderajan.cosmosnow.screenshots.screens

import androidx.compose.runtime.Composable
import com.maderajan.cosmosnow.feature.newsdetail.changefont.ChangeNewsFontScreenPreview
import com.maderajan.cosmosnow.screenshots.BaseComposableScreenshotTest

class ChangeNewsFontScreenTest: BaseComposableScreenshotTest() {

    @Composable
    override fun Compose(isDarkTheme: Boolean) {
        ChangeNewsFontScreenPreview()
    }
}