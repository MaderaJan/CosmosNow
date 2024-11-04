package com.maderajan.cosmosnow.data.repository.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews
import com.maderajan.cosmosnow.data.model.comosnews.SearchQuery

interface ICosmosNewsRepository {

    suspend fun getArticles(searchQuery: SearchQuery? = null): List<CosmosNews>

    suspend fun getBlogs(searchQuery: SearchQuery? = null): List<CosmosNews>

    suspend fun getReports(searchQuery: SearchQuery? = null): List<CosmosNews>

    suspend fun getInfo(): List<String>
}

