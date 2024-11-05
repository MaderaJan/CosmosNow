package com.maderajan.cosmosnow.data.repository.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.SearchQuery
import com.maderajan.cosmosnow.data.repository.mapper.cosmosnews.ArticleResponseToCosmosNewsMapper
import com.maderajan.cosmosnow.data.repository.mapper.cosmosnews.BlogResponseToCosmosNewsMapper
import com.maderajan.cosmosnow.data.repository.mapper.cosmosnews.ReportResponseToCosmosNewsMapper
import com.maderajan.cosmosnow.database.preferences.PreferencesStorage
import com.maderajan.cosmosnow.webservice.api.SpaceFlightsNewsApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CosmosNewsRepository @Inject constructor(
    private val spaceFlightsNewsApi: SpaceFlightsNewsApi,
    private val preferencesStorage: PreferencesStorage,
    private val articleResponseToCosmosNewsMapper: ArticleResponseToCosmosNewsMapper,
    private val blogResponseToCosmosNewsMapper: BlogResponseToCosmosNewsMapper,
    private val reportResponseToCosmosNewsMapper: ReportResponseToCosmosNewsMapper
) : ICosmosNewsRepository {

    override suspend fun getArticles(searchQuery: SearchQuery?): List<CosmosNews> =
        spaceFlightsNewsApi.getArticles(
            searchText = searchQuery?.searchText,
            newsSites = searchQuery?.newsSites,
            dateFrom = searchQuery?.dateFrom,
            dateTo = searchQuery?.dateTo,
        ).results.map(articleResponseToCosmosNewsMapper::map)

    override suspend fun getBlogs(searchQuery: SearchQuery?): List<CosmosNews> =
        spaceFlightsNewsApi.getBlogs(
            searchText = searchQuery?.searchText,
            newsSites = searchQuery?.newsSites,
            dateFrom = searchQuery?.dateFrom,
            dateTo = searchQuery?.dateTo,
        ).results.map(blogResponseToCosmosNewsMapper::map)

    override suspend fun getReports(searchQuery: SearchQuery?): List<CosmosNews> =
        spaceFlightsNewsApi.getReports(
            searchText = searchQuery?.searchText,
            newsSites = searchQuery?.newsSites,
            dateFrom = searchQuery?.dateFrom,
            dateTo = searchQuery?.dateTo,
        ).results.map(reportResponseToCosmosNewsMapper::map)

    override suspend fun getInfo(): List<String> =
        spaceFlightsNewsApi.getInfo()
            .newsSites

    override fun getFontSize(): Flow<Int> =
        preferencesStorage.getFontSize()

    override suspend fun updateFontSize(fontSize: Int) {
        preferencesStorage.updateFontSize(fontSize)
    }
}