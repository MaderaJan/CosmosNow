package com.maderajan.cosmosnow.feature.search

import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.NavigationCommand
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
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

            }

            SearchNewsUiAction.OpenDateSelect -> {

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
    val date: String? = null,
    val hasLaunch: Boolean? = null,
)

sealed interface SearchNewsUiAction : UiAction {
    data class SearchTextChanged(val text: String) : SearchNewsUiAction
    data object OpenNewsSiteOptions : SearchNewsUiAction
    data object OpenNewsTypeOptions : SearchNewsUiAction
    data object OpenDateSelect : SearchNewsUiAction
    data object OpenLaunchOptions : SearchNewsUiAction
    data class LaunchChanged(val hasLaunch: Boolean?) : SearchNewsUiAction
}

