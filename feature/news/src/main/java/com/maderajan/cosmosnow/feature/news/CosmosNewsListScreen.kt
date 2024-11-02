package com.maderajan.cosmosnow.feature.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.imageLoader
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNowTopBar
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing
import com.maderajan.cosmosnow.core.designsystem.util.dayMonthYearReadableDate
import com.maderajan.cosmosnow.core.designsystem.util.toWeekdayMonthReadableDate
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType

@Composable
fun CosmosNewsListScreen(
    newsListViewModel: CosmosNewsListViewModel = hiltViewModel()
) {
    val news = newsListViewModel.news.collectAsState().value

    Scaffold(
        topBar = {
            CosmosNowTopBar(
                title = stringResource(id = R.string.news_title),
                date = System.currentTimeMillis().toWeekdayMonthReadableDate()
            )
        }
    ) { paddingValues ->
        if (news.isEmpty()) {
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                TopNewsListItem(
                    news = news.first(),
                    onNewsClick = {
                        // TODO
                    },
                    onBookmarkClick = {
                        // TODO
                    }
                )
            }

            item {
                NewsDivider(startPadding = MaterialTheme.spacing.medium)
            }

            items(news.subList(1, news.size)) { item ->
                Column {
                    NewsListItem(
                        news = item,
                        onNewsClick = {
                            // TODO
                        },
                        onBookmarkClick = {
                            // TODO
                        }
                    )

                    NewsDivider(startPadding = MaterialTheme.spacing.mediumLarge)
                }
            }
        }
    }
}

@Composable
private fun NewsDivider(
    startPadding: Dp
) {
    HorizontalDivider(
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f),
        modifier = Modifier.padding(
            start = startPadding,
            end = MaterialTheme.spacing.medium
        )
    )
}

@Composable
fun NewsListItem(
    news: CosmosNews,
    onNewsClick: (CosmosNews) -> Unit,
    onBookmarkClick: (CosmosNews) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                onNewsClick(news)
            })
            .padding(MaterialTheme.spacing.medium)
    ) {
        val (imageRef, titleRef, newsSiteRef, timeRef, bookmarkRef) = createRefs()

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(news.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = news.title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.medium,
                    start = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.medium,
                )
                .constrainAs(titleRef) {
                    start.linkTo(imageRef.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            text = news.newsSite,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.medium)
                .constrainAs(newsSiteRef) {
                    top.linkTo(titleRef.bottom)
                    start.linkTo(titleRef.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_bookmark), // TODO FILE
            contentDescription = null,
            modifier = Modifier
                .clickable(onClick = {
                    onBookmarkClick(news)
                })
                .constrainAs(bookmarkRef) {
                    bottom.linkTo(imageRef.bottom)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = news.publishedAt.dayMonthYearReadableDate(),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.medium)
                .constrainAs(timeRef) {
                    start.linkTo(titleRef.start)
                    bottom.linkTo(imageRef.bottom)
                }
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

        val imageLoader = LocalContext.current.imageLoader.newBuilder()
            .logger(DebugLogger())
            .build()

        AsyncImage(
            imageLoader = imageLoader,
            model = ImageRequest.Builder(LocalContext.current)
                .data(news.imageUrl)
                .crossfade(true)
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

        Text(
            text = news.newsSite,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .constrainAs(newsSiteRef) {
                    top.linkTo(titleRef.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(timeRef.start)
                    width = Dimension.fillToConstraints
                }
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_bookmark), // TODO FILE
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
fun NewsListItemPreview() {
    CosmosNowTheme {
        NewsListItem(
            news = CosmosNews(
                id = 123L,
                title = "List item news Title which is loooonger",
                type = CosmosNewsType.ARTICLE,
                newsSite = "News Site",
                imageUrl = "",
                publishedAt = "2024-11-01T22:34:26Z"
            ),
            onNewsClick = {},
            onBookmarkClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsListItemPreview() {
    CosmosNowTheme {
        TopNewsListItem(
            news = CosmosNews(
                id = 123L,
                title = "Top News Title",
                type = CosmosNewsType.ARTICLE,
                newsSite = "News Site",
                imageUrl = "",
                publishedAt = "2024-11-01T22:34:26Z"
            ),
            onNewsClick = {},
            onBookmarkClick = {}
        )
    }
}