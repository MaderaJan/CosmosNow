package com.maderajan.cosmosnow.data.repository.bookmarks

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.repository.mapper.cosmosnews.CosmosNewsEntityToCosmosNewsMapper
import com.maderajan.cosmosnow.data.repository.mapper.cosmosnews.CosmosNewsToCosmosNewsEntityMapper
import com.maderajan.cosmosnow.database.dao.CosmosNewsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkRepository @Inject constructor(
    private val cosmosNewsDao: CosmosNewsDao,
    private val cosmosNewsToCosmosNewsEntityMapper: CosmosNewsToCosmosNewsEntityMapper,
    private val cosmosNewsEntityToCosmosNewsMapper: CosmosNewsEntityToCosmosNewsMapper
) : IBookmarkRepository {

    override suspend fun saveBookmark(news: CosmosNews) {
        val entity = cosmosNewsToCosmosNewsEntityMapper.map(news)
        cosmosNewsDao.persist(entity)
    }

    override fun getAllBookmarksFlow(): Flow<List<CosmosNews>> =
        cosmosNewsDao.selectAll()
            .map {
                it.map(cosmosNewsEntityToCosmosNewsMapper::map)
            }

    override suspend fun deleteBookmark(id: Long) {
        cosmosNewsDao.deleteById(id)
    }
}