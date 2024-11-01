package com.maderajan.cosmosnow.feature.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CosmosNewsListScreen(
    newsListViewModel: CosmosNewsListViewModel = hiltViewModel()
) {
    val news = newsListViewModel.news.collectAsState().value

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        news.forEach {
            Text(text = it.title)
        }
    }
}