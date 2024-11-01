package com.maderajan.cosmosnow.data.repository

import com.maderajan.cosmosnow.data.model.CosmosNews

interface ICosmosNewsRepository {

    suspend fun getArticles(): List<CosmosNews>

    suspend fun getBlogs(): List<CosmosNews>

    suspend fun getReports(): List<CosmosNews>

    suspend fun getInfo(): List<String>
}

