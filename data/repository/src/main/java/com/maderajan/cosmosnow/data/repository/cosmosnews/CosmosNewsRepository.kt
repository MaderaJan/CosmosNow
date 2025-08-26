package com.maderajan.cosmosnow.data.repository.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.SearchQuery
import com.maderajan.cosmosnow.data.repository.mapper.MapperFacade
import com.maderajan.cosmosnow.database.preferences.PreferencesStorage
import com.maderajan.cosmosnow.webservice.api.SpaceFlightsNewsApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CosmosNewsRepository @Inject constructor(
    private val spaceFlightsNewsApi: SpaceFlightsNewsApi,
    private val preferencesStorage: PreferencesStorage,
    private val mapperFacade: MapperFacade
) : ICosmosNewsRepository {

    override suspend fun getArticles(searchQuery: SearchQuery?): List<CosmosNews> =
        spaceFlightsNewsApi.getArticles(
            searchText = searchQuery?.searchText,
            newsSites = searchQuery?.newsSites,
            dateFrom = searchQuery?.dateFrom,
            dateTo = searchQuery?.dateTo,
        ).results.map {
            mapperFacade.articleResponseToCosmosNewsMapper.map(it)
        }

    override suspend fun getBlogs(searchQuery: SearchQuery?): List<CosmosNews> =
        spaceFlightsNewsApi.getBlogs(
            searchText = searchQuery?.searchText,
            newsSites = searchQuery?.newsSites,
            dateFrom = searchQuery?.dateFrom,
            dateTo = searchQuery?.dateTo,
        ).results.map {
            mapperFacade.blogResponseToCosmosNewsMapper.map(it)
        }

    override suspend fun getReports(searchQuery: SearchQuery?): List<CosmosNews> =
        spaceFlightsNewsApi.getReports(
            searchText = searchQuery?.searchText,
            newsSites = searchQuery?.newsSites,
            dateFrom = searchQuery?.dateFrom,
            dateTo = searchQuery?.dateTo,
        ).results.map {
            mapperFacade.reportResponseToCosmosNewsMapper.map(it)
        }

    override suspend fun getInfo(): List<String> =
        spaceFlightsNewsApi.getInfo()
            .newsSites

    override fun getFontSize(): Flow<Int> =
        preferencesStorage.getFontSize()

    override suspend fun updateFontSize(fontSize: Int) {
        preferencesStorage.updateFontSize(fontSize)
    }
}