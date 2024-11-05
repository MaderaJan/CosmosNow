package com.maderajan.cosmosnow.screenshots.screens

import androidx.compose.runtime.Composable
import com.maderajan.cosmosnow.feature.newsdetail.CosmosNewsDetailScreenPreview
import com.maderajan.cosmosnow.screenshots.BaseComposableScreenshotTest

class CosmosNewsDetailScreenTest: BaseComposableScreenshotTest() {

    @Composable
    override fun Compose(isDarkTheme: Boolean) {
        CosmosNewsDetailScreenPreview(isDarkTheme)
    }
}