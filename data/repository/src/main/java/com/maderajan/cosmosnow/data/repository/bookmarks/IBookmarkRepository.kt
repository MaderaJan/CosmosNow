package com.maderajan.cosmosnow.data.repository.bookmarks

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import kotlinx.coroutines.flow.Flow

interface IBookmarkRepository {

    suspend fun saveBookmark(news: CosmosNews)

    fun getAllBookmarksFlow(): Flow<List<CosmosNews>>

    suspend fun deleteBookmark(id: Long)
}