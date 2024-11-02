package com.maderajan.cosmosnow.data.repository.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.webservice.api.SpaceFlightsNewsApi
import javax.inject.Inject

class CosmosNewsRepository @Inject constructor(
    private val spaceFlightsNewsApi: SpaceFlightsNewsApi,
    private val articleResponseToCosmosNewsMapper: com.maderajan.cosmosnow.data.repository.mapper.ArticleResponseToCosmosNewsMapper,
    private val blogResponseToCosmosNewsMapper: com.maderajan.cosmosnow.data.repository.mapper.BlogResponseToCosmosNewsMapper,
    private val reportResponseToCosmosNewsMapper: com.maderajan.cosmosnow.data.repository.mapper.ReportResponseToCosmosNewsMapper
) : ICosmosNewsRepository {

    override suspend fun getArticles(offset: Int): List<CosmosNews> =
        spaceFlightsNewsApi.getArticles(offset)
            .results.map(articleResponseToCosmosNewsMapper::map)

    override suspend fun getBlogs(): List<CosmosNews> =
        spaceFlightsNewsApi.getBlogs()
            .results.map(blogResponseToCosmosNewsMapper::map)

    override suspend fun getReports(): List<CosmosNews> =
        spaceFlightsNewsApi.getReports()
            .results.map(reportResponseToCosmosNewsMapper::map)

    override suspend fun getInfo(): List<String> =
        spaceFlightsNewsApi.getInfo()
            .newsSites
}