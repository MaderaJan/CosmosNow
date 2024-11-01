package com.maderajan.cosmosnow.feature.news

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNowTopBar

@Composable
fun CosmosNewsListScreen(
    newsListViewModel: CosmosNewsListViewModel = hiltViewModel()
) {
    val news = newsListViewModel.news.collectAsState().value

    Scaffold(
        topBar = {
            CosmosNowTopBar(
                title = "Cosmos Now",
                date = "24. September 2024"
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(news) {
                Text(
                    text = it.title
                )
            }
        }
    }
}