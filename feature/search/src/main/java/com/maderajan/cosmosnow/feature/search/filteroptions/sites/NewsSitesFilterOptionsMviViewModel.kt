package com.maderajan.cosmosnow.feature.search.filteroptions.sites

import androidx.lifecycle.viewModelScope
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseMviViewModel
import com.maderajan.cosmosnow.domain.cosmosnews.SearchNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSitesFilterOptionsMviViewModel @Inject constructor(
    private val navigator: Navigator,
    private val searchNewsUseCase: SearchNewsUseCase
) : BaseMviViewModel<NewsSitesFilterOptionsUiState, NewsSitesFilterOptionsUiAction>(NewsSitesFilterOptionsUiState()) {

    override fun handleAction(action: NewsSitesFilterOptionsUiAction) {
        when (action) {
            is NewsSitesFilterOptionsUiAction.ProvideData -> {
                viewModelScope.launch {
                    searchNewsUseCase.getNewsSitesOrderByName()
                        .catch {
                            setUiState(NewsSitesFilterOptionsUiState(isError = true))
                        }
                        .collectLatest { allSites ->
                            setUiState(
                                NewsSitesFilterOptionsUiState(
                                    selectedSites = action.newsSites,
                                    allSites = allSites,
                                    isLoading = false
                                )
                            )
                        }
                }
            }

            is NewsSitesFilterOptionsUiAction.SiteChecked -> {
                val updatedSelectedSites = searchNewsUseCase.modifySelectedSites(action.isChecked, action.site, uiState.value.selectedSites)
                setUiState { copy(selectedSites = updatedSelectedSites) }
            }

            NewsSitesFilterOptionsUiAction.ApplyFilter -> {
                navigator.navigateUpWithResult(CosmosScreens.SearchNewsFilterNewsSites.RESULT_KEY, uiState.value.selectedSites)
            }

            NewsSitesFilterOptionsUiAction.ClearFilter -> {
                setUiState { copy(selectedSites = emptyList()) }
            }

            NewsSitesFilterOptionsUiAction.NavigateBack -> {
                navigator.navigateUp()
            }
        }
    }
}