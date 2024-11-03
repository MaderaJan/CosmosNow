package com.maderajan.cosmosnow.feature.news

import androidx.lifecycle.viewModelScope
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.NavigationCommand
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.domain.cosmosnews.BookmarkUseCase
import com.maderajan.cosmosnow.domain.cosmosnews.CosmosNewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CosmosNewsListViewModel @Inject constructor(
    cosmosNewsListUseCase: CosmosNewsListUseCase,
    private val bookmarkUseCase: BookmarkUseCase,
    private val navigator: Navigator
) : BaseViewModel<CosmosNewsListUiAction>() {

    val uiState: StateFlow<CosmosNewsListUiState> =
        cosmosNewsListUseCase.getSortedNewsFlow()
            .map<List<CosmosNews>, CosmosNewsListUiState>(CosmosNewsListUiState::Success)
            .catch {
                emit(CosmosNewsListUiState.Error)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CosmosNewsListUiState.Loading,
            )

    override fun handleAction(action: CosmosNewsListUiAction) {
        when (action) {
            is CosmosNewsListUiAction.BookMarkNews -> {
                viewModelScope.launch {
                    bookmarkUseCase.toggleBookmark(action.cosmosNews)
                }
            }

            is CosmosNewsListUiAction.OpenNews -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.CosmosNewsDetail(action.cosmosNews)))
            }

            is CosmosNewsListUiAction.TryAgain -> {
                // TODO TRY AGAIN
            }
        }
    }
}

sealed interface CosmosNewsListUiAction : UiAction {
    data object TryAgain : CosmosNewsListUiAction
    data class OpenNews(val cosmosNews: CosmosNews) : CosmosNewsListUiAction
    data class BookMarkNews(val cosmosNews: CosmosNews) : CosmosNewsListUiAction
}