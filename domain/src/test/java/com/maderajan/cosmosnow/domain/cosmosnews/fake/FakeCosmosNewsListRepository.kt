package com.maderajan.cosmosnow.domain.cosmosnews.fake

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.SearchQuery
import com.maderajan.cosmosnow.data.repository.cosmosnews.ICosmosNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCosmosNewsListRepository: ICosmosNewsRepository {

    var articlesFake = emptyList<CosmosNews>()
    var blogFake = emptyList<CosmosNews>()
    var reportsFake = emptyList<CosmosNews>()
    var sitesFake = emptyList<String>()
    var fontSize = 16

    override suspend fun getArticles(searchQuery: SearchQuery?): List<CosmosNews> =
        articlesFake

    override suspend fun getBlogs(searchQuery: SearchQuery?): List<CosmosNews> =
        blogFake

    override suspend fun getReports(searchQuery: SearchQuery?): List<CosmosNews> =
        reportsFake

    override suspend fun getInfo(): List<String> =
        sitesFake

    override fun getFontSize(): Flow<Int> =
        flowOf(fontSize)

    override suspend fun updateFontSize(fontSize: Int) {
        this.fontSize = fontSize
    }
}