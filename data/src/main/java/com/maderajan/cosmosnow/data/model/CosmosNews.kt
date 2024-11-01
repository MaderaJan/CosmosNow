package com.maderajan.cosmosnow.data.model

data class CosmosNews(
    val id: Long,
    val title: String,
    val type: CosmosNewsType,
    val newsSize: String
)

