package com.maderajan.cosmosnow.feature.search.filteroptions.sites

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.NoContent
import com.maderajan.cosmosnow.core.designsystem.component.NoContentDefaults
import com.maderajan.cosmosnow.core.designsystem.component.RowCheckbox
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing
import com.maderajan.cosmosnow.feature.search.components.FilterContent

@Composable
fun NewsSitesFilterOptionsScreen(
    uiState: NewsSitesFilterOptionsUiState,
    dispatchAction: (NewsSitesFilterOptionsUiAction) -> Unit
) {
    FilterContent(
        title = stringResource(id = R.string.search_filter_sites),
        hideApplyFilter = uiState.isLoading || uiState.isError,
        onCancelClick = {
            dispatchAction(NewsSitesFilterOptionsUiAction.NavigateBack)
        },
        onApplyClick = {
            dispatchAction(NewsSitesFilterOptionsUiAction.ApplyFilter)
        },
        onClearClick = {
            dispatchAction(NewsSitesFilterOptionsUiAction.ClearFilter)
        },
        content = {
            when {
                uiState.isError -> {
                    NoContent(
                        noContentData = NoContentDefaults.default(onButtonClick = null),
                        modifier = Modifier
                            .padding(
                                start = MaterialTheme.spacing.large,
                                end = MaterialTheme.spacing.large,
                                bottom = MaterialTheme.spacing.large
                            )
                            .align(Alignment.CenterHorizontally)
                    )
                }

                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }

                else -> {
                    LazyColumn {
                        items(uiState.allSites) { site ->
                            RowCheckbox(
                                text = site,
                                isChecked = uiState.selectedSites.any { it == site },
                                onCheckedChanged = { isChecked ->
                                    dispatchAction(NewsSitesFilterOptionsUiAction.SiteChecked(isChecked = isChecked, site))
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun NewsSitesFilterOptionsScreenPreview(isDarkTheme: Boolean = false) {
    CosmosNowTheme(isDarkTheme) {
        NewsSitesFilterOptionsScreen(
            uiState = NewsSitesFilterOptionsUiState(
                allSites = listOf("NASA", "SpaceX", "SpaceNews"),
                selectedSites = listOf("NASA", "SpaceNews"),
                isLoading = false
            ),
            dispatchAction = {}
        )
    }
}