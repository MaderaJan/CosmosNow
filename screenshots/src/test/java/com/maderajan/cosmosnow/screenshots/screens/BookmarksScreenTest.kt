package com.maderajan.cosmosnow.screenshots.screens

import androidx.compose.runtime.Composable
import com.maderajan.cosmosnow.feature.bookmarks.BookmarksScreenPreview
import com.maderajan.cosmosnow.screenshots.BaseComposableScreenshotTest

class BookmarksScreenTest: BaseComposableScreenshotTest() {

    @Composable
    override fun Compose(isDarkTheme: Boolean) {
        BookmarksScreenPreview(isDarkTheme)
    }
}