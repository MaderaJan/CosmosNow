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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CosmosNewsListViewModel @Inject constructor(
    private val navigator: Navigator,
    private val cosmosNewsListUseCase: CosmosNewsListUseCase,
    private val bookmarkUseCase: BookmarkUseCase,
) : BaseViewModel<CosmosNewsListUiAction>() {

    val uiState = MutableStateFlow(CosmosNewsListUiState())

    override fun handleAction(action: CosmosNewsListUiAction) {
        when (action) {
            CosmosNewsListUiAction.Start -> {
                viewModelScope.launch {
                    cosmosNewsListUseCase.getSortedNewsFlow()
                        .catch {
                            uiState.value = uiState.value.copy(isError = true)
                        }
                        .collect { news ->
                            uiState.value = CosmosNewsListUiState(news = news, isLoading = false, isError = false)
                        }
                }
            }

            is CosmosNewsListUiAction.BookMarkNews -> {
                viewModelScope.launch {
                    bookmarkUseCase.toggleBookmark(action.cosmosNews)
                }
            }

            is CosmosNewsListUiAction.OpenNews -> {
                navigator.navigate(NavigationCommand.NavigateToRoute(CosmosScreens.CosmosNewsDetail(action.cosmosNews)))
            }
        }
    }
}

sealed interface CosmosNewsListUiAction : UiAction {
    data object Start : CosmosNewsListUiAction
    data class OpenNews(val cosmosNews: CosmosNews) : CosmosNewsListUiAction
    data class BookMarkNews(val cosmosNews: CosmosNews) : CosmosNewsListUiAction
}