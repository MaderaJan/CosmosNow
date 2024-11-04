package com.maderajan.cosmosnow.feature.search

import androidx.lifecycle.viewModelScope
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.NavigationCommand
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.data.model.comosnews.SearchDate
import com.maderajan.cosmosnow.domain.cosmosnews.BookmarkUseCase
import com.maderajan.cosmosnow.domain.cosmosnews.CosmosNewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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
                viewModelScope.launch {
                    cosmosNewsUseCase.getSearchSortedNews(
                        searchText = uiState.value.searchText,
                        newsSites = uiState.value.newsSites,
                        types = uiState.value.types,
                        date = uiState.value.date,
                        hasLaunch = uiState.value.hasLaunch
                    ).collectLatest { news ->
                        uiState.value = uiState.value.copy(news = news)
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

data class SearchNewsUiState(
    val searchText: String = "",
    val newsSites: List<String> = emptyList(),
    val types: List<CosmosNewsType> = emptyList(),
    val date: SearchDate? = null,
    val hasLaunch: Boolean? = null,
    val news: List<CosmosNews> = emptyList()
)

sealed interface SearchNewsUiAction : UiAction {
    data class SearchTextChanged(val text: String) : SearchNewsUiAction
    data object OpenNewsSiteOptions : SearchNewsUiAction
    data object OpenNewsTypeOptions : SearchNewsUiAction
    data class NewsSitesSelected(val newsSites: List<String>) : SearchNewsUiAction
    data class TypesChanged(val types: List<CosmosNewsType>) : SearchNewsUiAction
    data class DateSelected(val date: SearchDate?) : SearchNewsUiAction
    data object OpenDateSelect : SearchNewsUiAction
    data object OpenLaunchOptions : SearchNewsUiAction
    data class LaunchChanged(val hasLaunch: Boolean?) : SearchNewsUiAction
    data object Search : SearchNewsUiAction

    data class OpenNews(val cosmosNews: CosmosNews) : SearchNewsUiAction
    data class BookMarkNews(val cosmosNews: CosmosNews) : SearchNewsUiAction
}

