package com.maderajan.cosmosnow.feature.search

import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.core.viewmodel.UiAction
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
    val searchText: String = ""
)

sealed interface SearchNewsUiAction : UiAction {
    data class SearchTextChanged(val text: String) : SearchNewsUiAction
}

