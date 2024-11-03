package com.maderajan.cosmosnow.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing
import com.maderajan.cosmosnow.core.designsystem.util.dayMonthYearReadableDate

@Composable
fun CosmosNewsListItem(
    title: String,
    imageUrl: String?,
    newsSite: String,
    type: String,
    publishedAt: String,
    onItemClicked: () -> Unit,
    onBookmarkClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClicked)
            .padding(MaterialTheme.spacing.medium)
    ) {
        val (imageRef, titleRef, newsSiteRef, timeRef, bookmarkRef) = createRefs()

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .placeholder(drawableResId = R.drawable.ic_news_placeholder)
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
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.medium,
                )
                .constrainAs(titleRef) {
                    start.linkTo(imageRef.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )

        NewsInfo(
            newsSite = newsSite,
            type = type,
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
            painter = painterResource(id = R.drawable.ic_bookmark),
            contentDescription = null,
            modifier = Modifier
                .clickable(onClick = onBookmarkClick)
                .constrainAs(bookmarkRef) {
                    bottom.linkTo(imageRef.bottom)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = publishedAt.dayMonthYearReadableDate(),
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
fun NewsDivider(
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


@Preview(showBackground = true)
@Composable
fun CosmosNewsListItemPreview() {
    CosmosNowTheme {
        CosmosNewsListItem(
            title = "Cosmos New Item",
            imageUrl = null,
            newsSite = "NASA",
            type = "Blog",
            publishedAt = "2024-11-01T22:34:26Z",
            onItemClicked = {},
            onBookmarkClick = {},
        )
    }
}
