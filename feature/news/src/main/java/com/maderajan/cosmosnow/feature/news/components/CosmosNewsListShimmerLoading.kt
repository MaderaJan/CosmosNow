package com.maderajan.cosmosnow.feature.news.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maderajan.cosmosnow.core.designsystem.component.ShimmerBrushBuilder
import com.maderajan.cosmosnow.core.designsystem.component.ShimmerItem
import com.maderajan.cosmosnow.core.designsystem.theme.spacing

@Composable
fun CosmosNewsListShimmerLoading() {
    ShimmerBrushBuilder()
        .add(ShimmerItem.Space(MaterialTheme.spacing.medium))
        .add(ShimmerItem.Box(200.dp))
        .add(ShimmerItem.Space(MaterialTheme.spacing.medium))
        .add(ShimmerItem.BoxText)
        .add(ShimmerItem.Space(MaterialTheme.spacing.medium))
        .add(ShimmerItem.BoxText)
        .add(ShimmerItem.Space(MaterialTheme.spacing.medium))
        .add(ShimmerItem.BoxText)
        .add(ShimmerItem.Space(MaterialTheme.spacing.medium))
        .add(ShimmerItem.BoxText)
        .add(ShimmerItem.Space(MaterialTheme.spacing.medium))
        .add(ShimmerItem.BoxText)
        .add(ShimmerItem.Space(MaterialTheme.spacing.medium))
        .add(ShimmerItem.BoxText)
        .Compose(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium))
}

@Preview
@Composable
fun CosmosNewsListShimmerLoadingPreview() {
    CosmosNewsListShimmerLoading()
}