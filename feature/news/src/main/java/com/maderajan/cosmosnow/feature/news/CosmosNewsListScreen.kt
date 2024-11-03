package com.maderajan.cosmosnow.feature.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNewsListItem
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNowTopBar
import com.maderajan.cosmosnow.core.designsystem.component.NewsDivider
import com.maderajan.cosmosnow.core.designsystem.component.NewsInfo
import com.maderajan.cosmosnow.core.designsystem.component.NoContent
import com.maderajan.cosmosnow.core.designsystem.component.NoContentDefaults
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing
import com.maderajan.cosmosnow.core.designsystem.util.dayMonthYearReadableDate
import com.maderajan.cosmosnow.core.designsystem.util.toWeekdayMonthReadableDate
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType

@Composable
fun CosmosNewsListScreen(
    uiState: CosmosNewsListUiState,
    dispatchAction: (CosmosNewsListUiAction) -> Unit
) {
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
            contentAlignment = if (uiState is CosmosNewsListUiState.Success) {
                Alignment.TopStart
            } else {
                Alignment.Center
            },
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (uiState) {
                CosmosNewsListUiState.Loading -> {
                    CircularProgressIndicator()
                }

                CosmosNewsListUiState.Error -> {
                    NoContent(
                        noContentData = NoContentDefaults.default(
                            onButtonClick = {
                                dispatchAction(CosmosNewsListUiAction.TryAgain)
                            },
                        ),
                    )
                }

                is CosmosNewsListUiState.Success -> {
                    LazyColumn {
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
                }
            }
        }
    }
}

@Preview
@Composable
fun CosmosNewsListScreenSuccessPreview() {
    CosmosNowTheme {
        CosmosNewsListScreen(
            uiState = CosmosNewsListUiState.Success(
                news = listOf(
                    CosmosNews.fake(title = "Top News Title"),
                    CosmosNews.fake(),
                    CosmosNews.fake(),
                    CosmosNews.fake(),
                    CosmosNews.fake(),
                ),
            ),
            dispatchAction = {}
        )
    }
}

@Composable
fun TopNewsListItem(
    news: CosmosNews,
    onNewsClick: (CosmosNews) -> Unit,
    onBookmarkClick: (CosmosNews) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
            .clickable(onClick = {
                onNewsClick(news)
            })
    ) {
        val (imageRef, titleRef, newsSiteRef, timeRef, bookmarkRef) = createRefs()

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(news.imageUrl)
                .crossfade(true)
                .placeholder(drawableResId = R.drawable.ic_news_placeholder)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .height(200.dp)
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = news.title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small,
                )
                .constrainAs(titleRef) {
                    top.linkTo(imageRef.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(bookmarkRef.start)
                    width = Dimension.fillToConstraints
                }
        )

        NewsInfo(
            newsSite = news.newsSite,
            type = stringResource(id = news.type.getPresentableNameRes()),
            modifier = Modifier
                .constrainAs(newsSiteRef) {
                    top.linkTo(titleRef.bottom)
                    start.linkTo(titleRef.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_bookmark),
            contentDescription = null,
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.small)
                .clickable(onClick = {
                    onBookmarkClick(news)
                })
                .constrainAs(bookmarkRef) {
                    end.linkTo(parent.end)
                    top.linkTo(imageRef.bottom)
                }
        )

        Text(
            text = news.publishedAt.dayMonthYearReadableDate(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.constrainAs(timeRef) {
                bottom.linkTo(newsSiteRef.bottom)
                end.linkTo(parent.end)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsListItemPreview() {
    CosmosNowTheme {
        TopNewsListItem(
            news = CosmosNews.fake(title = "Top news title"),
            onNewsClick = {},
            onBookmarkClick = {}
        )
    }
}

// TODO
fun CosmosNewsType.getPresentableNameRes(): Int =
    when (this) {
        CosmosNewsType.ARTICLE -> R.string.news_type_article
        CosmosNewsType.BLOG -> R.string.news_type_blog
        CosmosNewsType.REPORT -> R.string.news_type_report
    }