package com.maderajan.cosmosnow.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNowTopBar
import com.maderajan.cosmosnow.core.designsystem.component.DropDownChip
import com.maderajan.cosmosnow.core.designsystem.component.SearchField
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing

@Composable
fun SearchNewsRoute(
    viewModel: SearchNewsViewModel = hiltViewModel()
) {
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
            Column {
                CosmosNowTopBar(
                    title = stringResource(id = R.string.news_title),
                    date = null
                )

                SearchField(
                    text = uiState.searchText,
                    onTextChanged = {
                        dispatchAction(SearchNewsUiAction.SearchTextChanged(it))
                    }
                )

                Row(
                    Modifier.padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.medium,
                    )
                ) {

                    DropDownChip(
                        text = stringResource(id = R.string.search_filter_news),
                        isActive = false,
                        onClick = {
                            // TODO
                        },
                        modifier = Modifier.padding(end = MaterialTheme.spacing.small)
                    )

                    DropDownChip(
                        text = stringResource(id = R.string.search_filter_type),
                        isActive = false,
                        onClick = {
                            // TODO
                        },
                        modifier = Modifier.padding(end = MaterialTheme.spacing.small)
                    )

                    DropDownChip(
                        text = stringResource(id = R.string.search_filter_date),
                        isActive = false,
                        onClick = {
                            // TODO
                        },
                        modifier = Modifier.padding(end = MaterialTheme.spacing.small)
                    )

                    DropDownChip(
                        text = stringResource(id = R.string.search_filter_launch),
                        isActive = false,
                        onClick = {
                            // TODO
                        },
                        modifier = Modifier.padding(end = MaterialTheme.spacing.small)
                    )
                }
            }
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
            uiState = SearchNewsUiState(
                searchText = "Cosmos"
            ),
            dispatchAction = {}
        )
    }
}