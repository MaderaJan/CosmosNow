package com.maderajan.cosmosnow.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.data.model.comosnews.DateSelect
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
        savedStateHandle.getStateFlow<List<CosmosNewsType>>(
            key = CosmosScreens.SearchNewsFilterCosmosNewsType.RESULT_KEY,
            initialValue = emptyList()
        ).collect {
            viewModel.dispatch(SearchNewsUiAction.TypesChanged(it))
        }
    }

    LaunchedEffect(key1 = Unit) {
        savedStateHandle.getStateFlow<DateSelect?>(
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
            Box(modifier = Modifier.padding(paddingValues))
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