package com.maderajan.cosmosnow.feature.search

import androidx.lifecycle.viewModelScope
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.NavigationCommand
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseMviViewModel
import com.maderajan.cosmosnow.domain.cosmosnews.BookmarkUseCase
import com.maderajan.cosmosnow.domain.cosmosnews.CosmosNewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val cosmosNewsUseCase: CosmosNewsListUseCase,
    private val bookmarkUseCase: BookmarkUseCase
) : BaseMviViewModel<SearchNewsUiState, SearchNewsUiAction>(SearchNewsUiState()) {

    private val searchQueryTextFlow = MutableSharedFlow<String>()

    init {
        viewModelScope.launch {
            searchQueryTextFlow
                .debounce(500)
                .collect {
                    dispatch(SearchNewsUiAction.Search)
                }
        }
    }

    override fun handleAction(action: SearchNewsUiAction) {
        when (action) {
            is SearchNewsUiAction.SearchTextChanged -> {
                viewModelScope.launch {
                    searchQueryTextFlow.emit(action.text)
                }

                setUiState { copy(searchText = action.text) }
            }

            SearchNewsUiAction.OpenNewsSiteOptions -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.SearchNewsFilterNewsSites(uiState.value.newsSites)))
            }

            is SearchNewsUiAction.NewsSitesSelected -> {
                setUiState { copy(newsSites = action.newsSites) }
                dispatch(SearchNewsUiAction.Search)
            }

            SearchNewsUiAction.OpenNewsTypeOptions -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.SearchNewsFilterCosmosNewsType(uiState.value.types)))
            }

            is SearchNewsUiAction.TypesChanged -> {
                setUiState { copy(types = action.types) }
                dispatch(SearchNewsUiAction.Search)
            }

            SearchNewsUiAction.OpenDateSelect -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.SearchNewsFilterDate(uiState.value.date)))
            }

            is SearchNewsUiAction.DateSelected -> {
                setUiState { copy(date = action.date) }
                dispatch(SearchNewsUiAction.Search)
            }

            SearchNewsUiAction.OpenLaunchOptions -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.SearchNewsFilterLaunch(uiState.value.hasLaunch)))
            }

            is SearchNewsUiAction.LaunchChanged -> {
                setUiState { copy(hasLaunch = action.hasLaunch) }
                dispatch(SearchNewsUiAction.Search)
            }

            is SearchNewsUiAction.Search -> {
                setUiState { copy(isSearching = true) }

                viewModelScope.launch {
                    cosmosNewsUseCase.getSortedNewsByQuery(
                        searchText = uiState.value.searchText,
                        newsSites = uiState.value.newsSites,
                        types = uiState.value.types,
                        date = uiState.value.date,
                        hasLaunch = uiState.value.hasLaunch
                    ).catch {
                        setUiState { copy(isError = true, isSearching = false) }
                    }.collectLatest { news ->
                        setUiState { copy(news = news, isSearching = false, isError = false) }
                    }
                }
            }

            is SearchNewsUiAction.OpenNews -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.CosmosNewsDetail(action.cosmosNews)))
            }

            is SearchNewsUiAction.BookMarkNews -> {
                viewModelScope.launch {
                    bookmarkUseCase.toggleBookmark(action.cosmosNews)
                }
            }
        }
    }
}