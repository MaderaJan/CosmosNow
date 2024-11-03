package com.maderajan.cosmosnow.domain.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.repository.bookmarks.IBookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookmarkUseCase @Inject constructor(
    private val bookRepository: IBookmarkRepository
) {

    suspend fun saveBookmark(cosmosNews: CosmosNews) {
        bookRepository.saveBookmark(cosmosNews)
    }

    fun getAllBookmarks(): Flow<List<CosmosNews>> = flow {
        bookRepository.getAllBookmarks()
    }

    suspend fun deleteBookmark(cosmosNews: CosmosNews) {
        bookRepository.deleteBookmark(cosmosNews.id)
    }
}