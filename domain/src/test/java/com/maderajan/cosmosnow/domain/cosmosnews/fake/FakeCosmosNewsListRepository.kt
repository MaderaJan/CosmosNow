package com.maderajan.cosmosnow.domain.cosmosnews.fake

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.SearchQuery
import com.maderajan.cosmosnow.data.repository.cosmosnews.ICosmosNewsRepository

class FakeCosmosNewsListRepository: ICosmosNewsRepository {

    var articlesFake = emptyList<CosmosNews>()
    var blogFake = emptyList<CosmosNews>()
    var reportsFake = emptyList<CosmosNews>()
    var sitesFake = emptyList<String>()

    override suspend fun getArticles(searchQuery: SearchQuery?): List<CosmosNews> =
        articlesFake

    override suspend fun getBlogs(searchQuery: SearchQuery?): List<CosmosNews> =
        blogFake

    override suspend fun getReports(searchQuery: SearchQuery?): List<CosmosNews> =
        reportsFake

    override suspend fun getInfo(): List<String> =
        sitesFake
}