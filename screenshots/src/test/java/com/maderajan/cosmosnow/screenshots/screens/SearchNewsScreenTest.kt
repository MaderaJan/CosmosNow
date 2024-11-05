package com.maderajan.cosmosnow.screenshots.screens

import androidx.compose.runtime.Composable
import com.maderajan.cosmosnow.feature.search.SearchNewsScreenPreview
import com.maderajan.cosmosnow.screenshots.BaseComposableScreenshotTest

class SearchNewsScreenTest: BaseComposableScreenshotTest() {

    @Composable
    override fun Compose(isDarkTheme: Boolean) {
        SearchNewsScreenPreview(isDarkTheme)
    }
}