package com.maderajan.cosmosnow.data.repository.bookmarks

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.repository.mapper.MapperFacade
import com.maderajan.cosmosnow.database.dao.CosmosNewsDao
import com.maderajan.cosmosnow.database.entity.CosmosNewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkRepository @Inject constructor(
    private val cosmosNewsDao: CosmosNewsDao,
    private val mapperFacade: MapperFacade,
) : IBookmarkRepository {

    override suspend fun saveBookmark(news: CosmosNews) {
        val entity: CosmosNewsEntity = mapperFacade.map(news)
        cosmosNewsDao.persist(entity)
    }

    override fun getAllBookmarksFlow(): Flow<List<CosmosNews>> =
        cosmosNewsDao.selectAll()
            .map {
                it.map(mapperFacade::map)
            }

    override suspend fun deleteBookmark(id: Long) {
        cosmosNewsDao.deleteById(id)
    }
}