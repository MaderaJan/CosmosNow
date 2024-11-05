package com.maderajan.cosmosnow.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNewsListItem
import com.maderajan.cosmosnow.core.designsystem.component.NewsDivider
import com.maderajan.cosmosnow.core.designsystem.component.NoContent
import com.maderajan.cosmosnow.core.designsystem.component.NoContentData
import com.maderajan.cosmosnow.core.designsystem.component.NoContentDefaults
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
        contentWindowInsets = WindowInsets(0.dp),
        topBar = {
            CosmosNowSearchFilterBar(uiState, dispatchAction)
        },
        content = { paddingValues ->
            Box(
                contentAlignment = if (uiState.isError || uiState.news.isEmpty()) {
                    Alignment.Center
                } else {
                    Alignment.TopStart
                },
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                when {
                    !uiState.isSearching && uiState.isError -> {
                        NoContent(
                            noContentData = NoContentDefaults.default(
                                onButtonClick = {
                                    dispatchAction(SearchNewsUiAction.Search)
                                },
                            ),
                        )
                    }

                    !uiState.isSearching && uiState.news.isEmpty() -> {
                        NoContent(
                            noContentData = NoContentData(
                                title = stringResource(id = R.string.search_empty_title),
                                description = stringResource(id = R.string.search_empty_description),
                                icon = painterResource(id = R.drawable.ic_search)
                            )
                        )
                    }

                    else -> {
                        LazyColumn {
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