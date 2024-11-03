package com.maderajan.cosmosnow.webservice.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpaceFlightNewsResponseWrapper(
    val results: List<SpaceFlightNewsResponse>
)

@Serializable
data class SpaceFlightNewsResponse(
    val id: Long,
    val title: String,
    val url: String,
    val summary: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("news_site")
    val newsSite: String,
    @SerialName("published_at")
    val publishedAt: String,
)