package com.maderajan.cosmosnow.feature.bookmarks

import androidx.lifecycle.viewModelScope
import com.maderajan.cosmosnow.core.viewmodel.BaseViewModel
import com.maderajan.cosmosnow.core.viewmodel.UiAction
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.domain.cosmosnews.BookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    bookmarkUseCase: BookmarkUseCase
) : BaseViewModel<BookmarkUiAction>() {

    val uiState: StateFlow<BookmarkUiState> =
        bookmarkUseCase.getAllBookmarks()
            .map<List<CosmosNews>, BookmarkUiState>(BookmarkUiState::Success)
            .onEmpty {
                emit(BookmarkUiState.Empty)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = BookmarkUiState.Loading,
            )

    override fun handleAction(action: BookmarkUiAction) {
        TODO("Not yet implemented")
    }
}

sealed interface BookmarkUiState {
    data object Loading : BookmarkUiState
    data object Empty : BookmarkUiState
    data class Success(val news: List<CosmosNews>) : BookmarkUiState
}

sealed interface BookmarkUiAction : UiAction {
    data class OpenNews(val cosmosNews: CosmosNews) : BookmarkUiAction
    data class BookMarkNews(val cosmosNews: CosmosNews) : BookmarkUiAction
}