package com.maderajan.cosmosnow.navigation

import androidx.annotation.DrawableRes
import com.maderajan.cosmosnow.R
import com.maderajan.cosmosnow.feature.news.COSMOS_NEWS_ROUTE

enum class BottomNavigationItems(
    @DrawableRes val iconRes: Int,
    val route: String
) {
    NEWS(
        iconRes = R.drawable.ic_news_paper,
        route = COSMOS_NEWS_ROUTE
    ),
    SEARCH(
        iconRes = R.drawable.ic_search,
        route = "// TODO"
    ),
    BOOKMARKS(
        iconRes = R.drawable.ic_bookmarks,
        route = "// TODO"
    )
}