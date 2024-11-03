package com.maderajan.cosmosnow.navigation

import androidx.annotation.DrawableRes
import com.maderajan.cosmosnow.R
import com.maderajan.cosmosnow.core.navigation.CosmosScreens

enum class BottomNavigationItems(
    @DrawableRes val iconRes: Int,
    val screen: CosmosScreens
) {
    NEWS(
        iconRes = R.drawable.ic_news_paper,
        screen = CosmosScreens.CosmosNewsList
    ),
    SEARCH(
        iconRes = R.drawable.ic_search,
        screen = CosmosScreens.SearchNews
    ),
    BOOKMARKS(
        iconRes = R.drawable.ic_bookmarks,
        screen = CosmosScreens.Bookmarks
    )
}