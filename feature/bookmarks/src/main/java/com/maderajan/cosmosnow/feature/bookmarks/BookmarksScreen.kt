package com.maderajan.cosmosnow.feature.bookmarks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNewsListItem
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNowTopBar
import com.maderajan.cosmosnow.core.designsystem.component.NewsDivider
import com.maderajan.cosmosnow.core.designsystem.component.NoContent
import com.maderajan.cosmosnow.core.designsystem.component.NoContentData
import com.maderajan.cosmosnow.core.designsystem.theme.spacing
import com.maderajan.cosmosnow.data.model.comosnews.getPresentableNameRes

@Composable
fun BookmarksScreen(
    uiState: BookmarkUiState,
    dispatchAction: (BookmarkUiAction) -> Unit
) {
    Scaffold(
        topBar = {
            CosmosNowTopBar(
                title = stringResource(id = R.string.bookmarks_title),
                date = null
            )
        },
        content = { paddingValues ->
            Box(
                contentAlignment = if (uiState is BookmarkUiState.Success) {
                    Alignment.TopStart
                } else {
                    Alignment.Center
                },
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                when (uiState) {
                    BookmarkUiState.Loading -> {
                        CircularProgressIndicator()
                    }

                    BookmarkUiState.Empty -> {
                        NoContent(
                            noContentData = NoContentData(
                                title = stringResource(id = R.string.bookmarks_empty_title),
                                description = stringResource(id = R.string.bookmarks_empty_description),
                                icon = painterResource(id = R.drawable.ic_bookmark)
                            )
                        )
                    }

                    is BookmarkUiState.Success -> {
                        LazyColumn {
                            items(uiState.news) { item ->
                                Column {
                                    CosmosNewsListItem(
                                        title = item.title,
                                        imageUrl = item.imageUrl,
                                        newsSite = item.newsSite,
                                        type = stringResource(id = item.type.getPresentableNameRes()),
                                        publishedAt = item.publishedAt,
                                        isBookmarked = item.isBookmarked,
                                        onItemClicked = {
                                            dispatchAction(BookmarkUiAction.OpenNews(item))
                                        },
                                        onBookmarkClick = {
                                            dispatchAction(BookmarkUiAction.BookMarkNews(item))
                                        },
                                    )

                                    NewsDivider(startPadding = MaterialTheme.spacing.large)
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}