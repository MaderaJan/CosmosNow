package com.maderajan.cosmosnow.feature.search

import androidx.lifecycle.viewModelScope
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.NavigationCommand
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.domain.cosmosnews.BookmarkUseCase
import com.maderajan.cosmosnow.domain.cosmosnews.CosmosNewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val cosmosNewsUseCase: CosmosNewsListUseCase,
    private val bookmarkUseCase: BookmarkUseCase
) : BaseViewModel<SearchNewsUiAction>() {

    val uiState = MutableStateFlow(SearchNewsUiState())

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

                uiState.value = uiState.value.copy(searchText = action.text)
            }

            SearchNewsUiAction.OpenNewsSiteOptions -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.SearchNewsFilterNewsSites(uiState.value.newsSites)))
            }

            is SearchNewsUiAction.NewsSitesSelected -> {
                uiState.value = uiState.value.copy(newsSites = action.newsSites)
                dispatch(SearchNewsUiAction.Search)
            }

            SearchNewsUiAction.OpenNewsTypeOptions -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.SearchNewsFilterCosmosNewsType(uiState.value.types)))
            }

            is SearchNewsUiAction.TypesChanged -> {
                uiState.value = uiState.value.copy(types = action.types)
                dispatch(SearchNewsUiAction.Search)
            }

            SearchNewsUiAction.OpenDateSelect -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.SearchNewsFilterDate(uiState.value.date)))
            }

            is SearchNewsUiAction.DateSelected -> {
                uiState.value = uiState.value.copy(date = action.date)
                dispatch(SearchNewsUiAction.Search)
            }

            SearchNewsUiAction.OpenLaunchOptions -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.SearchNewsFilterLaunch(uiState.value.hasLaunch)))
            }

            is SearchNewsUiAction.LaunchChanged -> {
                uiState.value = uiState.value.copy(hasLaunch = action.hasLaunch)
                dispatch(SearchNewsUiAction.Search)
            }

            is SearchNewsUiAction.Search -> {
                uiState.value = uiState.value.copy(isSearching = true)

                viewModelScope.launch {
                    cosmosNewsUseCase.getSearchSortedNews(
                        searchText = uiState.value.searchText,
                        newsSites = uiState.value.newsSites,
                        types = uiState.value.types,
                        date = uiState.value.date,
                        hasLaunch = uiState.value.hasLaunch
                    ).collectLatest { news ->
                        uiState.value = uiState.value.copy(news = news, isSearching = false)
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