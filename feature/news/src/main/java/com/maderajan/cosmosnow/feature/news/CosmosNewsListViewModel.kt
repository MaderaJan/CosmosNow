package com.maderajan.cosmosnow.feature.news

import androidx.lifecycle.viewModelScope
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.domain.cosmosnews.CosmosNewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CosmosNewsListViewModel @Inject constructor(
    cosmosNewsListUseCase: CosmosNewsListUseCase
) : BaseViewModel<CosmosNewsListUiAction>() {

    val uiState: StateFlow<CosmosNewsListUiState> =
        cosmosNewsListUseCase.getSortedNews()
            .map<List<CosmosNews>, CosmosNewsListUiState>(CosmosNewsListUiState::Success)
            .onStart {
                emit(CosmosNewsListUiState.Loading)
            }
            .catch {
                emit(CosmosNewsListUiState.Error)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = CosmosNewsListUiState.Loading,
            )

    override fun handleAction(action: CosmosNewsListUiAction) {
        when (action) {
            is CosmosNewsListUiAction.BookMarkNews -> {
                // TODO BOOKMARK NEWS
            }

            is CosmosNewsListUiAction.OpenNews -> {
                // TODO OPEN NEWS
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