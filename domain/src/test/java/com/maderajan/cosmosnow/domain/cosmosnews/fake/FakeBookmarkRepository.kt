package com.maderajan.cosmosnow.domain.cosmosnews.fake

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.repository.bookmarks.IBookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBookmarkRepository: IBookmarkRepository {

    private val _fakeDatabase: MutableList<CosmosNews> = mutableListOf<CosmosNews>()

    override suspend fun saveBookmark(news: CosmosNews) {
        _fakeDatabase.add(news)
    }

    override fun getAllBookmarksFlow(): Flow<List<CosmosNews>> =
        flow { emit(_fakeDatabase) }

    override suspend fun deleteBookmark(id: Long) {
        _fakeDatabase.removeIf { id == it.id }
    }
}