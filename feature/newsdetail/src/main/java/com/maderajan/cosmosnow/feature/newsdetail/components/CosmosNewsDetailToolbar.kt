package com.maderajan.cosmosnow.feature.newsdetail.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.theme.spacing
import com.maderajan.cosmosnow.feature.newsdetail.CosmosNewsDetailUiAction
import com.maderajan.cosmosnow.feature.newsdetail.CosmosNewsDetailUiState

@Composable
fun CosmosNewsDetailToolbar(
    uiState: CosmosNewsDetailUiState,
    dispatchAction: (CosmosNewsDetailUiAction) -> Unit,
) {
    val context = LocalContext.current

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(MaterialTheme.spacing.small)
        ) {
            IconButton(
                onClick = {
                    dispatchAction(CosmosNewsDetailUiAction.NavigateBack)
                },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = null
                    )
                }
            )

            if (uiState is CosmosNewsDetailUiState.Success) {
                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        context.shareUrl(uiState.cosmosNews.url)
                    },
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = null
                        )
                    }
                )

                IconButton(
                    onClick = {
                        dispatchAction(CosmosNewsDetailUiAction.BookmarkNews(uiState.cosmosNews))
                    },
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bookmark),
                            contentDescription = null
                        )
                    }
                )
            }
        }

        if (uiState is CosmosNewsDetailUiState.Success) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .fillMaxWidth()
            ) {
                val (decorationLineRef, titleRef) = createRefs()

                Spacer(
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.small)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .width(4.dp)
                        .padding(vertical = MaterialTheme.spacing.small)
                        .constrainAs(decorationLineRef) {
                            start.linkTo(parent.start)
                            top.linkTo(titleRef.top)
                            bottom.linkTo(titleRef.bottom)
                            height = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = uiState.cosmosNews.title,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.constrainAs(titleRef) {
                        start.linkTo(decorationLineRef.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                )
            }
        }
    }
}

fun Context.shareUrl(url: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        setType("text/plain")
        putExtra(Intent.EXTRA_TEXT, url)
    }

    this.startActivity(Intent.createChooser(intent, "Share"))
}