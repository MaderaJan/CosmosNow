package com.maderajan.cosmosnow.feature.newsdetail

import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CosmosNewsDetailViewModel @Inject constructor(
    private val navigator: Navigator
): BaseViewModel<CosmosNewsDetailUiAction>() {

    val uiState: MutableStateFlow<CosmosNewsDetailUiState> = MutableStateFlow(CosmosNewsDetailUiState.Loading)

    override fun handleAction(action: CosmosNewsDetailUiAction) {
        when (action) {
            is CosmosNewsDetailUiAction.ProvideData -> {
                uiState.value = CosmosNewsDetailUiState.Success(action.cosmosNews)
            }

            CosmosNewsDetailUiAction.Bookmark -> {
                // TODO BOOKMARK
            }

            CosmosNewsDetailUiAction.NavigateBack -> {
                navigator.navigateUp()
            }
        }
    }
}