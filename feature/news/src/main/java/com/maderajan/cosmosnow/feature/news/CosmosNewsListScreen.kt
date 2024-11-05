package com.maderajan.cosmosnow.feature.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maderajan.cosmosnow.core.common.toWeekdayMonthReadableDate
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNewsListItem
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNowTopBar
import com.maderajan.cosmosnow.core.designsystem.component.NewsDivider
import com.maderajan.cosmosnow.core.designsystem.component.NoContent
import com.maderajan.cosmosnow.core.designsystem.component.NoContentDefaults
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.getPresentableNameRes
import com.maderajan.cosmosnow.feature.news.components.CosmosNewsListShimmerLoading
import com.maderajan.cosmosnow.feature.news.components.TopNewsListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CosmosNewsListScreen(
    uiState: CosmosNewsListUiState,
    dispatchAction: (CosmosNewsListUiAction) -> Unit
) {
    val pullRefreshState = rememberPullToRefreshState()

    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            CosmosNowTopBar(
                title = stringResource(id = R.string.news_title),
                date = System.currentTimeMillis().toWeekdayMonthReadableDate()
            )
        },
        bottomBar = {}
    ) { paddingValues ->
        Box(
            contentAlignment = if (!uiState.isError) {
                Alignment.TopStart
            } else {
                Alignment.Center
            },
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when {
                uiState.isError -> {
                    NoContent(
                        noContentData = NoContentDefaults.default(
                            onButtonClick = {
                                dispatchAction(CosmosNewsListUiAction.Start)
                            },
                        ),
                        modifier = Modifier.padding(MaterialTheme.spacing.medium)
                    )
                }

                uiState.isLoading -> {
                    CosmosNewsListShimmerLoading()
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.pullToRefresh(
                            state = pullRefreshState,
                            isRefreshing = uiState.isRefreshing,
                            onRefresh = {
                                dispatchAction(CosmosNewsListUiAction.PullToRefresh)
                            }
                        )
                    ) {
                        item {
                            TopNewsListItem(
                                news = uiState.news.first(),
                                onNewsClick = {
                                    dispatchAction(CosmosNewsListUiAction.OpenNews(it))
                                },
                                onBookmarkClick = {
                                    dispatchAction(CosmosNewsListUiAction.BookMarkNews(it))
                                }
                            )
                        }

                        item {
                            NewsDivider(startPadding = MaterialTheme.spacing.medium)
                        }

                        items(uiState.news.subList(1, uiState.news.size)) { item ->
                            Column {
                                CosmosNewsListItem(
                                    title = item.title,
                                    imageUrl = item.imageUrl,
                                    newsSite = item.newsSite,
                                    type = stringResource(id = item.type.getPresentableNameRes()),
                                    publishedAt = item.publishedAt,
                                    isBookmarked = item.isBookmarked,
                                    onItemClicked = {
                                        dispatchAction(CosmosNewsListUiAction.OpenNews(item))
                                    },
                                    onBookmarkClick = {
                                        dispatchAction(CosmosNewsListUiAction.BookMarkNews(item))
                                    }
                                )

                                NewsDivider(startPadding = MaterialTheme.spacing.large)
                            }
                        }
                    }

                    PullToRefreshDefaults.Indicator(
                        state = pullRefreshState,
                        isRefreshing = uiState.isRefreshing,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.TopCenter),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CosmosNewsListScreenPreview(isDarkTheme: Boolean = false) {
    CosmosNowTheme(isDarkTheme) {
        CosmosNewsListScreen(
            uiState = CosmosNewsListUiState(
                news = listOf(
                    CosmosNews.fake(title = "Top News Title"),
                    CosmosNews.fake(),
                    CosmosNews.fake(),
                    CosmosNews.fake(),
                    CosmosNews.fake(),
                ),
                isLoading = false
            ),
            dispatchAction = {}
        )
    }
}