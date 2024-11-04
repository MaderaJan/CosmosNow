package com.maderajan.cosmosnow.feature.search

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNewsListItem
import com.maderajan.cosmosnow.core.designsystem.component.NewsDivider
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.data.model.comosnews.SearchDate
import com.maderajan.cosmosnow.data.model.comosnews.getPresentableNameRes
import com.maderajan.cosmosnow.feature.search.components.CosmosNowSearchFilterBar

@Composable
fun SearchNewsRoute(
    savedStateHandle: SavedStateHandle,
    viewModel: SearchNewsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        savedStateHandle.getStateFlow<Boolean?>(
            key = CosmosScreens.SearchNewsFilterLaunch.RESULT_KEY,
            initialValue = null
        ).collect {
            viewModel.dispatch(SearchNewsUiAction.LaunchChanged(it))
        }
    }

    LaunchedEffect(key1 = Unit) {
        savedStateHandle.getStateFlow<List<String>>(
            key = CosmosScreens.SearchNewsFilterNewsSites.RESULT_KEY,
            initialValue = emptyList()
        ).collect {
            viewModel.dispatch(SearchNewsUiAction.NewsSitesSelected(it))
        }
    }

    LaunchedEffect(key1 = Unit) {
        savedStateHandle.getStateFlow<List<CosmosNewsType>>(
            key = CosmosScreens.SearchNewsFilterCosmosNewsType.RESULT_KEY,
            initialValue = emptyList()
        ).collect {
            viewModel.dispatch(SearchNewsUiAction.TypesChanged(it))
        }
    }

    LaunchedEffect(key1 = Unit) {
        savedStateHandle.getStateFlow<SearchDate?>(
            key = CosmosScreens.SearchNewsFilterDate.RESULT_KEY,
            initialValue = null
        ).collect {
            viewModel.dispatch(SearchNewsUiAction.DateSelected(it))
        }
    }

    SearchNewsScreen(
        uiState = viewModel.uiState.collectAsState().value,
        dispatchAction = viewModel::dispatch
    )
}

@Composable
fun SearchNewsScreen(
    uiState: SearchNewsUiState,
    dispatchAction: (SearchNewsUiAction) -> Unit
) {
    Scaffold(
        topBar = {
            CosmosNowSearchFilterBar(uiState, dispatchAction)
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues)
            ) {
                items(uiState.news) { item ->
                    CosmosNewsListItem(
                        title = item.title,
                        imageUrl = item.imageUrl,
                        newsSite = item.newsSite,
                        type = stringResource(id = item.type.getPresentableNameRes()),
                        publishedAt = item.publishedAt,
                        isBookmarked = item.isBookmarked,
                        onItemClicked = {
                            dispatchAction(SearchNewsUiAction.OpenNews(item))
                        },
                        onBookmarkClick = {
                            dispatchAction(SearchNewsUiAction.BookMarkNews(item))
                        }
                    )

                    NewsDivider(startPadding = MaterialTheme.spacing.large)
                }
            }
        }
    )
}

@Preview
@Composable
fun SearchNewsScreenPreview() {
    CosmosNowTheme {
        SearchNewsScreen(
            uiState = SearchNewsUiState(),
            dispatchAction = {}
        )
    }
}