package com.maderajan.cosmosnow.data.repository.mapper.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.repository.mapper.Mapper
import com.maderajan.cosmosnow.database.entity.CosmosNewsEntity
import javax.inject.Inject

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