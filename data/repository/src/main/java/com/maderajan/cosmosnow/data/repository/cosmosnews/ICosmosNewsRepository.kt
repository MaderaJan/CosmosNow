package com.maderajan.cosmosnow.data.repository.cosmosnews

import com.maderajan.cosmosnow.data.model.comosnews.CosmosNews

interface ICosmosNewsRepository {

    suspend fun getArticles(offset: Int): List<CosmosNews>

    suspend fun getBlogs(): List<CosmosNews>

    suspend fun getReports(): List<CosmosNews>

    suspend fun getInfo(): List<String>
}

