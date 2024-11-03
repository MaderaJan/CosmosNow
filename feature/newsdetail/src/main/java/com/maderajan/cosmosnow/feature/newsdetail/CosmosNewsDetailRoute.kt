package com.maderajan.cosmosnow.feature.newsdetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews

@Composable
fun CosmosNewsDetailRoute(
    cosmosNews: CosmosNews
) {
    CosmosNewsDetailScreen(cosmosNews)
}

@Composable
fun CosmosNewsDetailScreen(
    cosmosNews: CosmosNews
) {
    Text(text = cosmosNews?.title ?: "was null")
}