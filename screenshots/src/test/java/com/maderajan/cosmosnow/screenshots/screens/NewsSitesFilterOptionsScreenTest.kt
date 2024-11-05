package com.maderajan.cosmosnow.screenshots.screens

import androidx.compose.runtime.Composable
import com.maderajan.cosmosnow.feature.search.filteroptions.sites.NewsSitesFilterOptionsScreenPreview
import com.maderajan.cosmosnow.screenshots.BaseComposableScreenshotTest

class NewsSitesFilterOptionsScreenTest: BaseComposableScreenshotTest() {

    @Composable
    override fun Compose(isDarkTheme: Boolean) {
        NewsSitesFilterOptionsScreenPreview(isDarkTheme)
    }
}