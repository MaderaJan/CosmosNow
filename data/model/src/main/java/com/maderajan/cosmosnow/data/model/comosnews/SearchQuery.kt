package com.maderajan.cosmosnow.data.model.comosnews


data class SearchQuery(
    val searchText: String,
    val newsSites: String,
//        val types: List<CosmosNewsType>,
    val dateFrom: String?,
    val dateTo: String?,
    val hasLaunch: Boolean?,
)