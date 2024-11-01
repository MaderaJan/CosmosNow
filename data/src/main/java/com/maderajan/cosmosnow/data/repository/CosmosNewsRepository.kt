package com.maderajan.cosmosnow.data.repository

import com.maderajan.cosmosnow.data.mapper.ArticleResponseToCosmosNewsMapper
import com.maderajan.cosmosnow.data.mapper.BlogResponseToCosmosNewsMapper
import com.maderajan.cosmosnow.data.mapper.ReportResponseToCosmosNewsMapper
import com.maderajan.cosmosnow.data.model.CosmosNews
import com.maderajan.cosmosnow.webservice.api.SpaceFlightsNewsApi
import javax.inject.Inject

class CosmosNewsRepository @Inject constructor(
    private val spaceFlightsNewsApi: SpaceFlightsNewsApi,
    private val articleResponseToCosmosNewsMapper: ArticleResponseToCosmosNewsMapper,
    private val blogResponseToCosmosNewsMapper: BlogResponseToCosmosNewsMapper,
    private val reportResponseToCosmosNewsMapper: ReportResponseToCosmosNewsMapper
) : ICosmosNewsRepository {

    override suspend fun getArticles(): List<CosmosNews> =
        spaceFlightsNewsApi.getArticles()
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