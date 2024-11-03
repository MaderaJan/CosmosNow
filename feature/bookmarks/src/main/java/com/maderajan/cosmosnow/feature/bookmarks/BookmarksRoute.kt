package com.maderajan.cosmosnow.feature.bookmarks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BookmarksRoute(
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    BookmarksScreen(
        uiState = viewModel.uiState.collectAsState().value,
        dispatchAction = viewModel::dispatch
    )
}