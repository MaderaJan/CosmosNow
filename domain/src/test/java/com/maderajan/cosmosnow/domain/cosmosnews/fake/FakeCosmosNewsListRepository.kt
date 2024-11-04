package com.maderajan.cosmosnow.domain.cosmosnews.fake

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.repository.cosmosnews.ICosmosNewsRepository

class FakeCosmosNewsListRepository: ICosmosNewsRepository {

    var articlesFake = emptyList<CosmosNews>()
    var blogFake = emptyList<CosmosNews>()
    var reportsFake = emptyList<CosmosNews>()
    var sitesFake = emptyList<String>()

    override suspend fun getArticles(): List<CosmosNews> =
        articlesFake

    override suspend fun getBlogs(): List<CosmosNews> =
        blogFake

    override suspend fun getReports(): List<CosmosNews> =
        reportsFake

    override suspend fun getInfo(): List<String> =
        sitesFake
}