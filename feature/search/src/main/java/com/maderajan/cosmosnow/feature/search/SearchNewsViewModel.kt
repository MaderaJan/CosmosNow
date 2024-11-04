package com.maderajan.cosmosnow.feature.search

import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor() : BaseViewModel<SearchNewsUiAction>() {

    val uiState = MutableStateFlow(SearchNewsUiState())

    override fun handleAction(action: SearchNewsUiAction) {
        TODO("Not yet implemented")
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
    data object OpenNewsSiteOptions: SearchNewsUiAction
    data object OpenNewsTypeOptions: SearchNewsUiAction
    data object OpenDateSelect: SearchNewsUiAction
    data object OpenLaunchOptions: SearchNewsUiAction
}

