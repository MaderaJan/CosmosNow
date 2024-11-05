package com.maderajan.cosmosnow.screenshots.screens

import androidx.compose.runtime.Composable
import com.maderajan.cosmosnow.feature.news.CosmosNewsListScreenPreview
import com.maderajan.cosmosnow.screenshots.BaseComposableScreenshotTest

class CosmosNewsListScreenTest: BaseComposableScreenshotTest() {

    @Composable
    override fun Compose(isDarkTheme: Boolean) {
        CosmosNewsListScreenPreview()
    }
}