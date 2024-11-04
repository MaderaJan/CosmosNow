package com.maderajan.cosmosnow.feature.search.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.CosmosNowTopBar
import com.maderajan.cosmosnow.core.designsystem.component.DropDownChip
import com.maderajan.cosmosnow.core.designsystem.component.SearchField
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.data.model.comosnews.getPresentableNameRes
import com.maderajan.cosmosnow.feature.search.SearchNewsUiAction
import com.maderajan.cosmosnow.feature.search.SearchNewsUiState

@Composable
fun CosmosNowSearchFilterBar(
    uiState: SearchNewsUiState,
    dispatchAction: (SearchNewsUiAction) -> Unit
) {
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
            Modifier
                .horizontalScroll(rememberScrollState())
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.medium,
                )
        ) {
            DropDownChip(
                text = if (uiState.newsSites.isEmpty()) {
                    stringResource(id = R.string.search_filter_site)
                } else {
                    uiState.newsSites.joinToString(", ")
                },
                isActive = uiState.newsSites.isNotEmpty(),
                onClick = {
                    dispatchAction(SearchNewsUiAction.OpenNewsSiteOptions)
                },
                modifier = Modifier.padding(end = MaterialTheme.spacing.small)
            )

            DropDownChip(
                text = if (uiState.types.isEmpty()) {
                    stringResource(id = R.string.search_filter_type)
                } else {
                    uiState.types.map { stringResource(id = it.getPresentableNameRes()) }.joinToString(", ")
                },
                isActive = uiState.types.isNotEmpty(),
                onClick = {
                    dispatchAction(SearchNewsUiAction.OpenNewsTypeOptions)
                },
                modifier = Modifier.padding(end = MaterialTheme.spacing.small)
            )

            DropDownChip(
                text = uiState.date ?: stringResource(id = R.string.search_filter_date),
                isActive = uiState.date != null,
                onClick = {
                    dispatchAction(SearchNewsUiAction.OpenDateSelect)
                },
                modifier = Modifier.padding(end = MaterialTheme.spacing.small)
            )

            DropDownChip(
                text = if (uiState.hasLaunch == null) {
                    stringResource(id = R.string.search_filter_launch)
                } else if (uiState.hasLaunch) {
                    stringResource(id = R.string.search_filter_launched)
                } else {
                    stringResource(id = R.string.search_filter_not_launched)
                },
                isActive = uiState.hasLaunch != null,
                onClick = {
                    dispatchAction(SearchNewsUiAction.OpenLaunchOptions)
                },
                modifier = Modifier.padding(end = MaterialTheme.spacing.small)
            )
        }
    }
}

@Preview
@Composable
fun CosmosNowSearchFilterBarFilledPreview() {
    CosmosNowTheme {
        CosmosNowSearchFilterBar(
            uiState = SearchNewsUiState(
                searchText = "SearchText",
                newsSites = listOf("NASA, SpaceX"),
                types = listOf(CosmosNewsType.ARTICLE),
                date = "Today",
                hasLaunch = true,
            ),
            dispatchAction = {}
        )
    }
}

@Preview
@Composable
fun CosmosNowSearchFilterBarEmptyPreview() {
    CosmosNowTheme {
        CosmosNowSearchFilterBar(
            uiState = SearchNewsUiState(),
            dispatchAction = {}
        )
    }
}