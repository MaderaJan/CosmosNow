package com.maderajan.cosmosnow.feature.search

import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.NavigationCommand
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.data.model.comosnews.DateSelect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val navigator: Navigator,
) : BaseViewModel<SearchNewsUiAction>() {

    val uiState = MutableStateFlow(SearchNewsUiState())

    override fun handleAction(action: SearchNewsUiAction) {
        when (action) {
            is SearchNewsUiAction.SearchTextChanged -> {
                // TODO debounce text
            }

            SearchNewsUiAction.OpenNewsSiteOptions -> {

            }

            SearchNewsUiAction.OpenNewsTypeOptions -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.SearchNewsFilterCosmosNewsType(uiState.value.types)))
            }

            is SearchNewsUiAction.TypesChanged -> {
                uiState.value = uiState.value.copy(types = action.types)
            }

            SearchNewsUiAction.OpenDateSelect -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.SearchNewsFilterDate(uiState.value.date)))
            }

            is SearchNewsUiAction.DateSelected -> {
                uiState.value = uiState.value.copy(date = action.date)
            }

            SearchNewsUiAction.OpenLaunchOptions -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.SearchNewsFilterLaunch(uiState.value.hasLaunch)))
            }

            is SearchNewsUiAction.LaunchChanged -> {
                uiState.value = uiState.value.copy(hasLaunch = action.hasLaunch)
            }
        }
    }
}

data class SearchNewsUiState(
    val searchText: String = "",
    val newsSites: List<String> = emptyList(),
    val types: List<CosmosNewsType> = emptyList(),
    val date: DateSelect? = null,
    val hasLaunch: Boolean? = null,
)

sealed interface SearchNewsUiAction : UiAction {
    data class SearchTextChanged(val text: String) : SearchNewsUiAction
    data object OpenNewsSiteOptions : SearchNewsUiAction
    data object OpenNewsTypeOptions : SearchNewsUiAction
    data class TypesChanged(val types: List<CosmosNewsType>) : SearchNewsUiAction
    data class DateSelected(val date: DateSelect?) : SearchNewsUiAction
    data object OpenDateSelect : SearchNewsUiAction
    data object OpenLaunchOptions : SearchNewsUiAction
    data class LaunchChanged(val hasLaunch: Boolean?) : SearchNewsUiAction
}

