package com.maderajan.cosmosnow.screenshots.screens

import androidx.compose.runtime.Composable
import com.maderajan.cosmosnow.feature.search.filteroptions.type.CosmosNewsTypeFilterOptionsScreenPreview
import com.maderajan.cosmosnow.screenshots.BaseComposableScreenshotTest

class CosmosNewsTypeFilterOptionsScreenTest : BaseComposableScreenshotTest() {

    @Composable
    override fun Compose(isDarkTheme: Boolean) {
        CosmosNewsTypeFilterOptionsScreenPreview()
    }
}