package com.maderajan.cosmosnow.feature.newsdetail

import android.content.Context
import android.net.Uri
import androidx.annotation.ColorInt
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNowButton
import com.maderajan.cosmosnow.core.designsystem.component.NewsInfo
import com.maderajan.cosmosnow.core.designsystem.theme.spacing
import com.maderajan.cosmosnow.core.designsystem.util.dayMonthYearReadableDate
import com.maderajan.cosmosnow.feature.newsdetail.components.CosmosNewsDetailToolbar


@Composable
fun CosmosNewsDetailScreen(
    uiState: CosmosNewsDetailUiState,
    dispatchAction: (CosmosNewsDetailUiAction) -> Unit
) {
    Scaffold(
        topBar = {
            CosmosNewsDetailToolbar(
                uiState = uiState,
                dispatchAction = dispatchAction,
            )
        },
        bottomBar = {
            if (uiState is CosmosNewsDetailUiState.Success) {
                val context = LocalContext.current
                val toolbarColor = MaterialTheme.colorScheme.background.toArgb()

                CosmosNowButton(
                    text = stringResource(id = R.string.news_detail_button_see_read_more),
                    onClick = {
                        launchCustomChromeTab(
                            context = context,
                            uri = Uri.parse(uiState.cosmosNews.url),
                            toolbarColor = toolbarColor
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.medium)
                )
            }
        },
        content = { paddingValues ->
            when (uiState) {
                CosmosNewsDetailUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is CosmosNewsDetailUiState.Success -> {
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .verticalScroll(rememberScrollState())
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(uiState.cosmosNews.imageUrl)
                                .crossfade(true)
                                .placeholder(drawableResId = R.drawable.ic_news_placeholder)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .padding(
                                    top = MaterialTheme.spacing.medium,
                                    start = MaterialTheme.spacing.medium,
                                    end = MaterialTheme.spacing.medium,
                                    bottom = MaterialTheme.spacing.extraSmall
                                )
                                .clip(RoundedCornerShape(16.dp))
                                .height(200.dp)
                        )

                        Row {
                            NewsInfo(
                                newsSite = uiState.cosmosNews.newsSite,
                                type = stringResource(id = uiState.cosmosNews.type.getPresentableNameRes()),
                                modifier = Modifier.padding(start = MaterialTheme.spacing.medium)
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                text = uiState.cosmosNews.publishedAt.dayMonthYearReadableDate(),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(end = MaterialTheme.spacing.medium)
                            )
                        }

                        Text(
                            text = uiState.cosmosNews.summary,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(MaterialTheme.spacing.medium)
                        )
                    }
                }
            }
        }
    )
}

// TODO MOVE
fun launchCustomChromeTab(
    context: Context,
    uri: Uri,
    @ColorInt toolbarColor: Int
) {
    val customTabBarColor = CustomTabColorSchemeParams.Builder()
        .setToolbarColor(toolbarColor).build()

    val customTabsIntent = CustomTabsIntent.Builder()
        .setDefaultColorSchemeParams(customTabBarColor)
        .build()

    customTabsIntent.launchUrl(context, uri)
}