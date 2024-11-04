package com.maderajan.cosmosnow.feature.search.filteroptions.sites

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.maderajan.cosmosnow.core.designsystem.R
import com.maderajan.cosmosnow.core.designsystem.component.RowCheckbox
import com.maderajan.cosmosnow.core.designsystem.theme.CosmosNowTheme
import com.maderajan.cosmosnow.core.designsystem.theme.spacing
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.domain.cosmosnews.SearchNewsUseCase
import com.maderajan.cosmosnow.feature.search.components.FilterContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsSitesFilterOptionsRoute(
    newsSites: List<String>,
    viewModel: NewsSitesFilterOptionsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.dispatch(NewsSitesFilterOptionsUiAction.ProvideData(newsSites = newsSites))
    }

    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        onDismissRequest = {
            viewModel.dispatch(NewsSitesFilterOptionsUiAction.NavigateBack)
        },
        content = {
            NewsSitesFilterOptionsScreen(
                uiState = viewModel.uiState.collectAsState().value,
                dispatchAction = viewModel::dispatch
            )
        },
        modifier = Modifier.padding(top = MaterialTheme.spacing.large)
    )
}

@Composable
fun NewsSitesFilterOptionsScreen(
    uiState: NewsSitesFilterOptionsUiState,
    dispatchAction: (NewsSitesFilterOptionsUiAction) -> Unit
) {
    FilterContent(
        title = stringResource(id = R.string.search_filter_sites),
        isLoading = uiState.isLoading,
        onCancelClick = {
            dispatchAction(NewsSitesFilterOptionsUiAction.NavigateBack)
        },
        onCtaClick = {
            dispatchAction(NewsSitesFilterOptionsUiAction.ApplyFilter)
        },
        content = {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.large)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
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
    )
}

@Preview
@Composable
fun NewsSitesFilterOptionsScreenPreview() {
    CosmosNowTheme {
        NewsSitesFilterOptionsScreen(
            uiState = NewsSitesFilterOptionsUiState(
                allSites = listOf("NASA", "SpaceX", "SpaceNews"),
                selectedSites = listOf("NASA", "SpaceNews"),
            ),
            dispatchAction = {}
        )
    }
}

@HiltViewModel
class NewsSitesFilterOptionsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val searchNewsUseCase: SearchNewsUseCase
) : BaseViewModel<NewsSitesFilterOptionsUiAction>() {

    val uiState = MutableStateFlow(NewsSitesFilterOptionsUiState())

    override fun handleAction(action: NewsSitesFilterOptionsUiAction) {
        when (action) {
            is NewsSitesFilterOptionsUiAction.ProvideData -> {
                // TODO LOADING

                viewModelScope.launch {
                    searchNewsUseCase.getNewsSitesOrderByName()
                        .collectLatest { allSites ->
                            uiState.value = NewsSitesFilterOptionsUiState(
                                selectedSites = action.newsSites,
                                allSites = allSites,
                                isLoading = false
                            )
                        }
                }
            }

            is NewsSitesFilterOptionsUiAction.SiteChecked -> {
                val updatedSelectedSites = searchNewsUseCase.modifySelectedSites(action.isChecked, action.site, uiState.value.selectedSites)
                uiState.value = uiState.value.copy(selectedSites = updatedSelectedSites)
            }

            NewsSitesFilterOptionsUiAction.ApplyFilter -> {
                navigator.navigateUpWithResult(CosmosScreens.SearchNewsFilterNewsSites.RESULT_KEY, uiState.value.selectedSites)
            }

            NewsSitesFilterOptionsUiAction.NavigateBack -> {
                navigator.navigateUp()
            }
        }
    }
}

data class NewsSitesFilterOptionsUiState(
    val selectedSites: List<String> = emptyList(),
    val allSites: List<String> = emptyList(),
    val isLoading: Boolean = true
)

sealed interface NewsSitesFilterOptionsUiAction : UiAction {
    data class ProvideData(val newsSites: List<String>) : NewsSitesFilterOptionsUiAction
    data class SiteChecked(val isChecked: Boolean, val site: String) : NewsSitesFilterOptionsUiAction
    data object NavigateBack : NewsSitesFilterOptionsUiAction
    data object ApplyFilter : NewsSitesFilterOptionsUiAction
}