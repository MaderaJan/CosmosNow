package com.maderajan.cosmosnow.feature.newsdetail

import androidx.lifecycle.viewModelScope
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.domain.cosmosnews.BookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CosmosNewsDetailViewModel @Inject constructor(
    private val navigator: Navigator,
    private val bookmarkUseCase: BookmarkUseCase,
) : BaseViewModel<CosmosNewsDetailUiAction>() {

    val uiState = MutableStateFlow(CosmosNewsDetailUiState())

    init {
        viewModelScope.launch {
            bookmarkUseCase.getAllBookmarksFlow()
                .collect { bookmarks ->
                    val isBookmarked = bookmarks.any { it.id == uiState.value.cosmosNews.id }
                    uiState.value = uiState.value.copy(cosmosNews = uiState.value.cosmosNews.copy(isBookmarked = isBookmarked))
                }
        }
    }

    override fun handleAction(action: CosmosNewsDetailUiAction) {
        when (action) {
            is CosmosNewsDetailUiAction.ProvideData -> {
                uiState.value = uiState.value.copy(cosmosNews = action.cosmosNews)
            }

            is CosmosNewsDetailUiAction.BookmarkNews -> {
                viewModelScope.launch {
                    bookmarkUseCase.toggleBookmark(action.cosmosNews)
                }
            }

            CosmosNewsDetailUiAction.NavigateBack -> {
                navigator.navigateUp()
            }
        }
    }
}