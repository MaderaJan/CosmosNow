package com.maderajan.cosmosnow.data.repository.bookmarks

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.enumFromKey
import com.maderajan.cosmosnow.data.repository.mapper.Mapper
import com.maderajan.cosmosnow.database.dao.CosmosNewsDao
import com.maderajan.cosmosnow.database.entity.CosmosNewsEntity
import javax.inject.Inject

interface IBookmarkRepository {

    suspend fun saveBookmark(news: CosmosNews)

    suspend fun getAllBookmarks(): List<CosmosNews>

    suspend fun deleteBookmark(id: Long)
}

class BookmarkRepository @Inject constructor(
    private val cosmosNewsDao: CosmosNewsDao,
    private val cosmosNewsToCosmosNewsEntityMapper: CosmosNewsToCosmosNewsEntityMapper,
    private val cosmosNewsEntityToCosmosNewsMapper: CosmosNewsEntityToCosmosNewsMapper
) : IBookmarkRepository {

    override suspend fun saveBookmark(news: CosmosNews) {
        val entity = cosmosNewsToCosmosNewsEntityMapper.map(news)
        cosmosNewsDao.persist(entity)
    }

    override suspend fun getAllBookmarks(): List<CosmosNews> =
        cosmosNewsDao.selectAll()
            .map(cosmosNewsEntityToCosmosNewsMapper::map)

    override suspend fun deleteBookmark(id: Long) {
        cosmosNewsDao.deleteById(id)
    }
}

class CosmosNewsToCosmosNewsEntityMapper @Inject constructor() : Mapper<CosmosNews, CosmosNewsEntity> {

    override fun map(from: CosmosNews): CosmosNewsEntity =
        CosmosNewsEntity(
            id = from.id,
            title = from.title,
            type = from.type.key,
            summary = from.summary,
            url = from.url,
            newsSite = from.newsSite,
            imageUrl = from.imageUrl,
            publishedAt = from.publishedAt,
        )
}

class CosmosNewsEntityToCosmosNewsMapper @Inject constructor() : Mapper<CosmosNewsEntity, CosmosNews> {

    override fun map(from: CosmosNewsEntity): CosmosNews =
        CosmosNews(
            id = from.id,
            title = from.title,
            type = enumFromKey(from.type),
            summary = from.summary,
            url = from.url,
            newsSite = from.newsSite,
            imageUrl = from.imageUrl,
            publishedAt = from.publishedAt,
        )
}