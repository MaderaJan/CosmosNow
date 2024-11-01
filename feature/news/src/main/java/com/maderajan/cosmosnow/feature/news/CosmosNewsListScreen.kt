package com.maderajan.cosmosnow.feature.news

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CosmosNewsListScreen(
    newsListViewModel: CosmosNewsListViewModel = hiltViewModel()
) {
    val news = newsListViewModel.news.collectAsState().value

    Column {
        news.forEach {
            Text(text = it.title)
        }
    }
}