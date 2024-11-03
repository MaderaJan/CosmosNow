package com.maderajan.cosmosnow.domain.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.repository.bookmarks.IBookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkUseCase @Inject constructor(
    private val bookRepository: IBookmarkRepository
) {

    suspend fun saveBookmark(cosmosNews: CosmosNews) {
        bookRepository.saveBookmark(cosmosNews)
    }

    fun getAllBookmarksFlow(): Flow<List<CosmosNews>> =
        bookRepository.getAllBookmarksFlow()

    suspend fun deleteBookmark(cosmosNews: CosmosNews) {
        bookRepository.deleteBookmark(cosmosNews.id)
    }
}