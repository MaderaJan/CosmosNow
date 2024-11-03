package com.maderajan.cosmosnow.domain.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.repository.bookmarks.IBookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkUseCase @Inject constructor(
    private val bookmarkRepository: IBookmarkRepository
) {

    fun getAllBookmarksFlow(): Flow<List<CosmosNews>> =
        bookmarkRepository.getAllBookmarksFlow()

    suspend fun toggleBookmark(cosmosNews: CosmosNews) {
        if (cosmosNews.isBookmarked) {
            bookmarkRepository.deleteBookmark(cosmosNews.id)
        } else {
            bookmarkRepository.saveBookmark(cosmosNews)
        }
    }
}