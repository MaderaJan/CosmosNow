package com.maderajan.cosmosnow.data.repository.mapper

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.CosmosNewsType
import com.maderajan.cosmosnow.webservice.response.SpaceFlightNewsResponse
import javax.inject.Inject

class ArticleResponseToCosmosNewsMapper @Inject constructor() : Mapper<SpaceFlightNewsResponse, CosmosNews> {

    override fun map(from: SpaceFlightNewsResponse): CosmosNews =
        from.toData(CosmosNewsType.ARTICLE)
}

fun SpaceFlightNewsResponse.toData(type: CosmosNewsType) =
    CosmosNews(
        id = this.id,
        title = this.title,
        type = type,
        newsSize = this.newsSite,
    )