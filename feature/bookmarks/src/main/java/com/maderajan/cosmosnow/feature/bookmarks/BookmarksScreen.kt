package com.maderajan.cosmosnow.feature.bookmarks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNowTopBar

@Composable
fun BookmarksScreen(
    uiState: BookmarkUiState,
    dispatchAction: (BookmarkUiAction) -> Unit
) {
    Scaffold(
        topBar = {
            CosmosNowTopBar(
                title = stringResource(id = R.string.bookmarks_title),
                date = null
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when (uiState) {
                    BookmarkUiState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is BookmarkUiState.Success -> {

                    }
                }
            }
        }
    )
}