package com.maderajan.cosmosnow.webservice.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponseWrapper(
    val results: List<ArticleResponse>
)

@Serializable
data class ArticleResponse(
    val id: Long,
    val title: String,
    val url: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("news_site")
    val newsSize: String
)