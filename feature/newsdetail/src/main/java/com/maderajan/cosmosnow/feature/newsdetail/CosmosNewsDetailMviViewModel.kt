package com.maderajan.cosmosnow.feature.newsdetail

import androidx.lifecycle.viewModelScope
import com.maderajan.cosmosnow.core.navigation.CosmosScreens
import com.maderajan.cosmosnow.core.navigation.Navigator
import com.maderajan.cosmosnow.core.viewmodel.BaseMviViewModel
import com.maderajan.cosmosnow.domain.cosmosnews.BookmarkUseCase
import com.maderajan.cosmosnow.domain.cosmosnews.NewsFontUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CosmosNewsDetailMviViewModel @Inject constructor(
    private val navigator: Navigator,
    private val bookmarkUseCase: BookmarkUseCase,
    private val newsFontUseCase: NewsFontUseCase
) : BaseMviViewModel<CosmosNewsDetailUiState, CosmosNewsDetailUiAction>(CosmosNewsDetailUiState()) {

    init {
        viewModelScope.launch {
            bookmarkUseCase.getAllBookmarksFlow()
                .collect { bookmarks ->
                    val isBookmarked = bookmarks.any { it.id == uiState.value.cosmosNews.id }
                    setUiState { copy(cosmosNews = uiState.value.cosmosNews.copy(isBookmarked = isBookmarked)) }
                }
        }

        viewModelScope.launch {
            newsFontUseCase.getFontSize()
                .collect { fontSize ->
                    setUiState { copy(fontSize = fontSize) }
                }
        }
    }

    override fun handleAction(action: CosmosNewsDetailUiAction) {
        when (action) {
            is CosmosNewsDetailUiAction.ProvideData -> {
                setUiState { copy(cosmosNews = action.cosmosNews) }
            }

            is CosmosNewsDetailUiAction.BookmarkNews -> {
                viewModelScope.launch {
                    bookmarkUseCase.toggleBookmark(action.cosmosNews)
                }
            }

            CosmosNewsDetailUiAction.OpenIncreaseFont -> {
                navigator.navigate(CosmosScreens.ChangeNewsFont)
            }

            CosmosNewsDetailUiAction.NavigateBack -> {
                navigator.navigateUp()
            }
        }
    }
}