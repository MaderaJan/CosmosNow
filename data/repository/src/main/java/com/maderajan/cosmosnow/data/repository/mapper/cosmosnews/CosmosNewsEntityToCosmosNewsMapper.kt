package com.maderajan.cosmosnow.data.repository.mapper.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.enumFromKey
import com.maderajan.cosmosnow.data.repository.mapper.Mapper
import com.maderajan.cosmosnow.database.entity.CosmosNewsEntity
import javax.inject.Inject

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
            isBookmarked = true
        )
}