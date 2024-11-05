package com.maderajan.cosmosnow.feature.news.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.maderajan.cosmosnow.core.common.dayMonthYearReadableDate
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.BookmarkIcon
import com.maderajan.cosmosnow.core.designsystem.component.NewsInfo
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.getPresentableNameRes

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
            contentScale = ContentScale.Crop,
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
                    top = 12.dp,
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
                    start.linkTo(titleRef.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(timeRef.bottom)
                    width = Dimension.fillToConstraints
                }
        )

        BookmarkIcon(
            isBookmarked = news.isBookmarked,
            onClick = {
                onBookmarkClick(news)
            },
            modifier = Modifier
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
                top.linkTo(bookmarkRef.bottom)
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