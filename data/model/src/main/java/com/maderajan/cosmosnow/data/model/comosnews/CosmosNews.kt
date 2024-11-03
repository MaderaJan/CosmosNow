package com.maderajan.cosmosnow.data.model.comosnews

import kotlinx.serialization.Serializable

@Serializable
data class CosmosNews(
    val id: Long,
    val title: String,
    val type: CosmosNewsType,
    val summary: String,
    val url: String,
    val newsSite: String,
    val imageUrl: String?,
    val publishedAt: String,
    val isBookmarked: Boolean,
) {
    companion object {
        fun fake(
            id: Long = 1,
            title: String = "New Title",
            publishedAt: String = "2024-11-01T22:34:26Z",
            isBookmarked: Boolean = false,
        ): CosmosNews = CosmosNews(
            id = id,
            title = title,
            type = CosmosNewsType.ARTICLE,
            summary = "ESA is ready to launch its Hera planetary defense mission just as soon as SpaceX’s Falcon 9 rocket is back in business. The launch window opens on Monday. SpaceX suspended...",
            newsSite = "News Site",
            url = "",
            imageUrl = null,
            publishedAt = publishedAt,
            isBookmarked = isBookmarked
        )
    }
}