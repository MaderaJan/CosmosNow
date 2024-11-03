package com.maderajan.cosmosnow.feature.newsdetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CosmosNewsDetailRoute(
    title: String
) {
    CosmosNewsDetailScreen(title)
}

@Composable
fun CosmosNewsDetailScreen(
    title: String
) {
    Text(text = title)
}